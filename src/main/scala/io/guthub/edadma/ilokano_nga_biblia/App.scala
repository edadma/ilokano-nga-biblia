package io.guthub.edadma.ilokano_nga_biblia

import io.github.edadma.translations.*

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

import scala.scalajs.js.Thenable.Implicits.*
import concurrent.ExecutionContext.Implicits.global
import typings.capacitorPreferences.mod.Preferences
import typings.capacitorPreferences.distEsmDefinitionsMod.{GetOptions, RemoveOptions, SetOptions}
import text.juan
import org.scalajs.dom.HTMLInputElement

import scala.collection.immutable.ArraySeq
import scala.scalajs.js.timers.setTimeout

import components.*

type Mode = "light" | "dark"
type View = "text" | "books"
type Book = ArraySeq[(String, Int)]

val bookVar = Var[Book](juan.book)
val bookSignal = bookVar.signal
val chapterVar = Var(1)
val chapterSignal = chapterVar.signal
val modeVar = Var[Mode]("light")
val modeSignal = modeVar.signal
val viewVar = Var[View]("text")
val viewSignal = viewVar.signal
val settingsVar = Var(false)

def App =
  setLanguages(
    """
      |en:
      |  search: Search
      |  books: Books
      |  text: Text
      |  booksOfTheBible: Books of the Bible
      |  missingBooks: Missing books will be added as they are translated.
      |ilo:
      |  search: Sapulen
      |  books: Libro
      |  text: Teksto
      |  booksOfTheBible: Dagiti Libro ti Biblia
      |  missingBooks: Mainayon dagiti awan a libro bayat ti pannakaipatarusda.
      |""".stripMargin,
  )
  setLanguage("ilo")

  Preferences.get(GetOptions("mode")) foreach { v =>
    v.value match
      case null                      =>
      case mode @ ("light" | "dark") => setMode(mode)
      case _                         => Preferences.remove(RemoveOptions("mode"))
  }

  Card(
    div(
      cls := "flex justify-between",
      Input(
        typ := "text",
        placeholder := t"search",
        cls := "sm:max-w-md",
        inContext(thisNode => onChange --> (_ => handleSearchInput(thisNode.ref))),
      ),
      Button(
        cls := "ml-2",
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
      Button(
        cls := "ml-2",
        SVG.userSettings,
        onClick --> { _ => settingsVar.set(true) },
      ),
      Button(
        cls := "ml-2",
        child <-- modeSignal.map(mode => if mode == "light" then SVG.moon else SVG.sun),
        onClick --> { _ =>
          val newMode: Mode = if modeSignal.now() == "light" then "dark" else "light"

          Preferences set SetOptions("mode", newMode)
          setMode(newMode)
        },
      ),
    ),
    div(
//      child <-- settingsSignal.map { settings =>
//        if settings then foreignHtmlElement(DomApi.unsafeParseHtmlString("""<div class="overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
//                                                                           |    <div class="relative p-4 w-full max-w-2xl max-h-full">
//                                                                           |        <!-- Modal content -->
//                                                                           |        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
//                                                                           |            <!-- Modal header -->
//                                                                           |            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
//                                                                           |                <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
//                                                                           |                    Static modal
//                                                                           |                </h3>
//                                                                           |                <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-hide="static-modal">
//                                                                           |                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
//                                                                           |                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
//                                                                           |                    </svg>
//                                                                           |                    <span class="sr-only">Close modal</span>
//                                                                           |                </button>
//                                                                           |            </div>
//                                                                           |            <!-- Modal body -->
//                                                                           |            <div class="p-4 md:p-5 space-y-4">
//                                                                           |                <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
//                                                                           |                    With less than a month to go before the European Union enacts new consumer privacy laws for its citizens, companies around the world are updating their terms of service agreements to comply.
//                                                                           |                </p>
//                                                                           |                <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
//                                                                           |                    The European Union’s General Data Protection Regulation (G.D.P.R.) goes into effect on May 25 and is meant to ensure a common set of data rights in the European Union. It requires organizations to notify users as soon as possible of high-risk data breaches that could personally affect them.
//                                                                           |                </p>
//                                                                           |            </div>
//                                                                           |            <!-- Modal footer -->
//                                                                           |            <div class="flex items-center p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
//                                                                           |                <button data-modal-hide="static-modal" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">I accept</button>
//                                                                           |                <button data-modal-hide="static-modal" type="button" class="ms-3 text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">Decline</button>
//                                                                           |            </div>
//                                                                           |        </div>
//                                                                           |    </div>
//                                                                           |</div>""".stripMargin))
//        else div()
//      },
      Modal(Text("asdf"))(settingsVar, "Settings"),
      child <-- viewSignal.map {
        case "text" =>
          div(
            div(
              cls := "flex justify-between",
              child <-- chapterSignal.map(ch =>
                if ch > 1 then
                  Button(
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
                  Button(
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
                .combineWith(chapterSignal)
                .map((book, chapter) => foreignHtmlElement(DomApi.unsafeParseHtmlString(book(chapter - 1)._1))),
            ),
          )
        case "books" =>
          div(
            Text(cls := "mt-6 mb-2 text-xl", t"booksOfTheBible"),
            books map { (name, book) => div(Button(name, onClick --> { _ => goToBook(book) })) },
            Text(cls := "mt-5", t"missingBooks"),
          )
      },
    ),
  )
end App

def handleSearchInput(ref: HTMLInputElement): Unit =
  def blur() =
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
