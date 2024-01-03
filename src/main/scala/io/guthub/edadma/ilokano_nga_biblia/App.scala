package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*
import org.scalajs.dom

import scala.scalajs.js.Thenable.Implicits.*
import concurrent.ExecutionContext.Implicits.global
import typings.capacitorPreferences.mod.Preferences
import typings.capacitorPreferences.distEsmDefinitionsMod.{GetOptions, RemoveOptions, SetOptions}
import io.guthub.edadma.ilokano_nga_biblia.text.juan
import org.scalajs.dom.HTMLInputElement

import scala.collection.immutable.ArraySeq
import scala.scalajs.js.timers.setTimeout

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

def App =
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
        placeholder := "Sapulen",
        cls := "sm:max-w-md",
        inContext(thisNode => onChange --> { _ => handleSearchInput(thisNode.ref) }),
      ),
      Button(
        cls := "ml-2",
        child.text <-- viewSignal.map {
          case "text"  => "Libro"
          case "books" => "Teksto"
        },
        onClick --> { _ =>
          viewVar.set(viewSignal.now() match
            case "text"  => "books"
            case "books" => "text",
          )
        },
      ),
      child <-- modeSignal.map(mode =>
        Button(
          if mode == "light" then SVG.moon else SVG.sun,
          cls := "ml-2",
          onClick --> { _ =>
            val newMode: Mode = if mode == "light" then "dark" else "light"

            Preferences set SetOptions("mode", newMode)
            setMode(newMode)
          },
        ),
      ),
    ),
    div(
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
                else div("books"),
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
            Text(cls := "mt-6 mb-2 text-xl", "Dagiti Libro ti Biblia"),
            books map { (name, book) => div(Button(name, onClick --> { _ => goToBook(book) })) },
            Text(cls := "mt-5", "Mainayon dagiti awan a libro bayat ti pannakaipatarusda."),
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

                if 1 <= verseNum && verseNum <= b(chap)._2 then
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
