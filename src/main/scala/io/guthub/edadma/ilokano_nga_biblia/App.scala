package io.guthub.edadma.ilokano_nga_biblia

import io.github.edadma.translations.*
import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

import scala.scalajs.js.Thenable.Implicits.*
import concurrent.ExecutionContext.Implicits.global
import typings.capacitorPreferences.mod.Preferences
import typings.capacitorPreferences.distEsmDefinitionsMod.{GetOptions, RemoveOptions, SetOptions}
import org.scalajs.dom.HTMLInputElement

import scala.collection.immutable.ArraySeq
import scala.scalajs.js.timers.setTimeout
import components.*

type Mode = "light" | "dark"
type View = "text" | "books"
type Book = ArraySeq[(String, Int)]
type Size = "lg" | "xl" | "2xl"

val bookVar = Var[Book](text.genesis.book)
val bookSignal = bookVar.signal
val chapterVar = Var(1)
val chapterSignal = chapterVar.signal
val modeVar = Var[Mode]("light")
val modeSignal = modeVar.signal
val viewVar = Var[View]("text")
val viewSignal = viewVar.signal
val settingsVar = Var(false)
val sizeVar = Var[Size]("lg")
val sizeSignal = sizeVar.signal
val aboutVar = Var(false)
val showImagesVar = Var(true)
val showImagesSignal = showImagesVar.signal

def App =
  setLanguages(
    """
      |en:
      |  search: Search
      |  books: Books
      |  text: Text
      |  booksOfTheBible: Books of the Bible
      |  missingBooks: Missing books will be added as they are translated.
      |  settings: Settings
      |  about: About
      |  aboutText: |
      |    <div class="prose prose-h1:font-normal prose-h1:text-gray-600 dark:prose-h1:text-gray-400 prose-h2:text-gray-600 dark:prose-h2:text-gray-400 prose-h3:text-gray-600 dark:prose-h3:text-gray-400 prose-p:text-xl prose-p:text-gray-600 dark:prose-p:text-gray-400">
      |      <h1>The Ilokano Bible</h1>
      |      <p>This application contains the Holy Bible translated into the Ilokano language.</p>
      |      <h3>Repositories</h3>
      |      <p>The repository for the Bible translation can be found at <a href="https://github.com/nasantuan-a-biblia/teksto-ti-biblia">Nasantuan a Biblia</a>.
      |         The repository for this application can be found at <a href="https://github.com/edadma/ilokano-nga-biblia">Ilokano nga Biblia</a>.</p>
      |      <h3>Version</h3>
      |      <p>March 5, 2024</p>
      |      <h3>License</h3>
      |      <p>This translation of the Holy Bible is in the public domain. The application is open source and is
      |         under the MIT license.</p>
      |    </div>
      |  oldTestament: Old Testament
      |  newTestament: New Testament
      |  showImages: Show images
      |ilo:
      |  search: Sapulen
      |  books: Libro
      |  text: Teksto
      |  booksOfTheBible: Dagiti Libro ti Biblia
      |  missingBooks: Mainayon dagiti awan a libro bayat ti pannakaipatarusda.
      |  settings: Dagiti Setting
      |  about: Maipapan
      |  aboutText: |
      |    <div class="prose prose-h1:font-normal prose-h1:text-gray-600 dark:prose-h1:text-gray-400 prose-h2:text-gray-600 dark:prose-h2:text-gray-400 prose-h3:text-gray-600 dark:prose-h3:text-gray-400 prose-p:text-xl prose-p:text-gray-600 dark:prose-p:text-gray-400">
      |      <h1>Ilokano nga Biblia</h1>
      |      <p>Daytoy nga aplikasion ket naglaon ti Nasantuan a Biblia a naipatarus iti pagsasao nga Ilokano.</p>
      |      <h3>Dagiti Pagidulin</h3>
      |      <p>Masarakan ti pagidulinan iti patarus ti Biblia iti <a href="https://github.com/nasantuan-a-biblia/teksto-ti-biblia">Nasantuan a Biblia</a>.
      |         Ti pagidulinan para iti daytoy nga aplikasion ket mabalin a masarakan iti <a href="https://github.com/edadma/ilokano-nga-biblia">Ilokano nga Biblia</a>.</p>
      |      <h3>Bersyon</h3>
      |      <p>Marso 14, 2024</p>
      |      <h3>Lisensia</h3>
      |      <p>Daytoy a patarus ti Nasantuan a Biblia ket adda iti publiko. Ti aplikasion ket open source ken isu ti
      |         lisensia ti MIT.</p>
      |    </div>
      |  oldTestament: Daan a Tulag
      |  newTestament: Baro a Tulag
      |  showImages: Ipakita dagiti ladawan
      |""".stripMargin,
  )
  setLanguage("ilo")

  Preferences.get(GetOptions("mode")) foreach { v =>
    v.value match
      case null                      =>
      case mode @ ("light" | "dark") => setMode(mode)
      case _                         => Preferences.remove(RemoveOptions("mode"))
  }

  Preferences.get(GetOptions("size")) foreach { v =>
    v.value match
      case null                         =>
      case size @ ("lg" | "xl" | "2xl") => setSize(size)
      case _                            => Preferences.remove(RemoveOptions("size"))
  }

  Preferences.get(GetOptions("showImages")) foreach { v =>
    v.value match
      case null                            =>
      case showImages @ ("true" | "false") => setShowImages(showImages == "true")
      case _                               => Preferences.remove(RemoveOptions("showImages"))
  }

  Card(
    cls := "h-screen",
    div(
      cls := "flex justify-between",
      Input(
        typ := "text",
        placeholder := t"search",
        cls := "sm:max-w-md",
        inContext(thisNode => onChange --> (_ => handleSearchInput(thisNode.ref))),
      ),
      Clickable(
        cls := "ml-2 p-2.5",
        child.text <-- viewSignal.map {
          case "text"  => t"books"
          case "books" => t"text"
        },
        onClick --> { _ =>
          viewVar.set(viewSignal.now() match
            case "text"  => "books"
            case "books" => "text",
          )
        },
      ),
      Clickable(
        cls := "ml-2 p-2.5",
        SVG.userSettings,
        onClick --> { _ => settingsVar.set(true) },
      ),
      Clickable(
        cls := "ml-2 p-2.5",
        SVG.about,
        onClick --> { _ => aboutVar.set(true) },
      ),
      Clickable(
        cls := "ml-2 p-2.5",
        child <-- modeSignal.map(mode => if mode == "light" then SVG.moon else SVG.sun),
        onClick --> { _ =>
          val newMode: Mode = if modeSignal.now() == "light" then "dark" else "light"

          Preferences set SetOptions("mode", newMode)
          setMode(newMode)
        },
      ),
    ),
    div(
      settingsModel,
      aboutModel,
      child <-- viewSignal.map {
        case "text" =>
          div(
            div(
              cls := "flex justify-between p-2.5",
              child <-- chapterSignal.map(ch =>
                if ch > 1 || bookSignal.now().length == 1 then
                  Clickable(
                    cls.toggle("invisible") := bookSignal.now().length == 1,
                    SVG.leftArrow,
                    onClick --> { _ =>
                      chapterVar.update(_ - 1)
                      scrollToTop()
                    },
                  )
                else div(),
              ),
              child <-- chapterSignal.map(ch =>
                if ch < bookSignal.now().length then
                  Clickable(
                    SVG.rightArrow,
                    onClick --> { _ =>
                      chapterVar.update(_ + 1)
                      scrollToTop()
                    },
                  )
                else div(),
              ),
            ),
            div(
              idAttr := "text",
              cls := "no-scrollbar overflow-auto h-[calc(100vh-135px)]",
              child <-- bookSignal
                .combineWith(chapterSignal, showImagesSignal)
                .map((book, chapter, showImages) =>
                  imagesMap get (book, chapter) match
                    case Some(png) if showImages =>
                      img(cls := "mb-5 w-full h-auto object-cover object-center", src := png)
                    case _ => emptyNode,
                ),
              child <-- bookSignal
                .combineWith(chapterSignal, sizeSignal)
                .map((book, chapter, size) =>
                  foreignHtmlElement(
                    DomApi.unsafeParseHtmlString(
                      FormatString(
                        book,
                        chapter,
                        Map("size" -> (size match {
                          case "lg"  => "prose-p:text-lg"
                          case "xl"  => "prose-p:text-xl"
                          case "2xl" => "prose-p:text-2xl"
                        })),
                      ),
                    ),
                  ),
                ),
            ),
          )
        case "books" =>
          div(
            Text(cls := "mt-6 mb-6 text-xl", t"booksOfTheBible"),
            div(
              cls := "flex flex-row",
              div(
                cls := "flex flex-col mr-10",
                Text(cls := "mb-2 text-l", t"oldTestament"),
//                (1 to 39) map (n => div(Clickable(cls := "leading-tight", n.toString)())),
                booksOT map { (name, book) =>
                  div(Clickable(cls := "leading-tight", name, onClick --> { _ => goToBook(book) }))
                },
              ),
              div(
                cls := "flex flex-col",
                Text(cls := "mb-2 text-l", t"newTestament"),
                booksNT map { (name, book) =>
                  div(Clickable(cls := "leading-tight", name, onClick --> { _ => goToBook(book) }))
                },
              ),
            ),
            Text(cls := "mt-5", t"missingBooks"),
          )
      },
    ),
  )
end App

def settingsModel =
  val btn1 = Button(
    cls := "font-gentium text-lg mr-2 w-14 h-9",
    onClick --> { _ => setSize("lg") },
    "A",
  )
  val btn2 = Button(
    cls := "font-gentium text-xl mr-2 w-14 h-9",
    onClick --> { _ => setSize("xl") },
    "A",
  )
  val btn3 =
    Button(cls := "font-gentium text-2xl w-14 h-9", onClick --> { _ => setSize("2xl") }, "A")

  Modal(
    div(btn1, btn2, btn3),
    div(
      cls := "flex items-center justify-between",
      Text(t"showImages"),
      Toggle(
        checked <-- showImagesSignal,
        inContext(thisNode => onChange --> (_ => setShowImages(thisNode.ref.checked))),
      ),
    ),
  )(
    settingsVar,
    t"settings",
    modalCls = "w-72", {
      val ref =
        (sizeSignal.now() match
          case "lg"  => btn1
          case "xl"  => btn2
          case "2xl" => btn3
        ).ref

      Seq(btn1, btn2, btn3).foreach { b =>
        b.ref.classList.remove("ring-4")
        b.ref.classList.remove("outline-none")
        b.ref.classList.remove("ring-blue-300")
        b.ref.classList.remove("dark:ring-blue-800")
      }
      ref.classList.add("ring-4")
      ref.classList.add("outline-none")
      ref.classList.add("ring-blue-300")
      ref.classList.add("dark:ring-blue-800")
    },
  )

def aboutModel =
  Modal(foreignHtmlElement(DomApi.unsafeParseHtmlString(t"aboutText".trim)))(
    aboutVar,
    t"about",
  )

def setSize(size: Size): Unit =
  sizeVar set size
  Preferences set SetOptions("size", size)

def setShowImages(setting: Boolean): Unit =
  showImagesVar set setting
  Preferences set SetOptions("showImages", if setting then "true" else "false")

def handleSearchInput(ref: HTMLInputElement): Unit =
  def blur(): Unit =
    ref.blur()
    ref.value = ""

  parseBibleReference(ref.value) match
    case None => // parse error
    case Some(book, chapter, verse) =>
      booksMap get book match
        case None => // book not found
        case Some(b) =>
          if chapter ne null then
            val chap = chapter.toInt

            if 1 <= chap && chap <= b.length then
              if verse ne null then
                val verseNum = verse.toInt

                if 1 <= verseNum && verseNum <= b(chap - 1)._2 then
                  bookVar.set(b)
                  chapterVar.set(chap)
                  viewVar.set("text")
                  blur()
                  Option(dom.document.getElementById(verse)).map(v => setTimeout(10)(v.scrollIntoView(true)))
                else {
                  // verse not found
                }
              else
                bookVar.set(b)
                chapterVar.set(chap)
                viewVar.set("text")
                scrollToTop()
                blur()
              end if
            else {
              // chapter not found
            }
            end if
          else
            goToBook(b)
            blur()
end handleSearchInput

def goToBook(book: Book): Unit =
  bookVar.set(book)
  chapterVar.set(1)
  viewVar.set("text")
  scrollToTop()

def scrollToTop(): Unit =
  val textElem = dom.document.getElementById("text")

  if textElem ne null then textElem.scrollTop = 0

def setMode(mode: Mode): Unit =
  modeVar set mode

  if mode == "dark" then dom.document.documentElement.classList.add("dark")
  else dom.document.documentElement.classList.remove("dark")

//<h3 class="mb-5 text-lg font-medium text-gray-900 dark:text-white">How much do you expect to use each month?</h3>
//<div class="grid w-full gap-6 md:grid-cols-2">
//  <div>
//    <input type="radio" id="hosting-small" name="hosting" value="hosting-small" class="peer hidden" required />
//    <label for="hosting-small" class="inline-block w-full cursor-pointer rounded-lg border border-gray-200 bg-white p-5 text-gray-500 hover:bg-gray-100 hover:text-gray-600 peer-checked:border-blue-600 peer-checked:text-blue-600 dark:border-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-gray-300 dark:peer-checked:text-blue-500">
//      <div class="inline-flex w-full cursor-pointer items-center justify-between">
//      <div>
//        <div class="text-lg font-semibold">0-50 MB</div>
//        <div class="">Good for small websites</div>
//      </div>
//      <svg class="ms-3 h-5 w-5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
//        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9" />
//    </svg>
//      </div>
//    </label>
//  </div>
//  <div>
//    <input type="radio" id="hosting-big" name="hosting" value="hosting-big" class="peer hidden" />
//    <label for="hosting-big" class="inline-flex w-full cursor-pointer items-center justify-between rounded-lg border border-gray-200 bg-white p-5 text-gray-500 hover:bg-gray-100 hover:text-gray-600 peer-checked:border-blue-600 peer-checked:text-blue-600 dark:border-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-gray-300 dark:peer-checked:text-blue-500">
//      <div class="block">
//        <div class="w-full text-lg font-semibold">500-1000 MB</div>
//        <div class="w-full">Good for large websites</div>
//      </div>
//      <svg class="ms-3 h-5 w-5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
//        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9" />
//      </svg>
//    </label>
//  </div>
//</div>
