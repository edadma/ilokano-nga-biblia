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
      |  settings: Settings
      |ilo:
      |  search: Sapulen
      |  books: Libro
      |  text: Teksto
      |  booksOfTheBible: Dagiti Libro ti Biblia
      |  missingBooks: Mainayon dagiti awan a libro bayat ti pannakaipatarusda.
      |  settings: Dagiti Setting
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
      Modal(Button("A"), Button("A"), Button("A"))(settingsVar, t"settings"),
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
                .map((book, chapter) =>
                  foreignHtmlElement(
                    DomApi.unsafeParseHtmlString(FormatString(book, chapter, Map("size" -> "prose-p:text-lg"))),
                  ),
                ),
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
