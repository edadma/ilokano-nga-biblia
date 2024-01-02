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
import org.scalajs.dom.{HTMLInputElement, MouseEvent}

type Mode = "light" | "dark"
type View = "text" | "books"

val bookVar = Var(juan.book)
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
        "Libro",
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
                .map((book, chapter) => foreignHtmlElement(DomApi.unsafeParseHtmlString(book(chapter - 1)))),
            ),
          )
        case "books" => div("asdf")
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
                val verseElem = dom.document.getElementById(verse)

                if verseElem ne null then
                  verseElem.scrollIntoView(true)
                  bookVar.set(b)
                  chapterVar.set(chap)
                  blur()
                else {
                  // verse not found
                }
                end if
              else
                bookVar.set(b)
                chapterVar.set(chap)
                scrollToTop()
                blur()
              end if
            else {
              // chapter not found
            }
            end if
          else
            bookVar.set(b)
            chapterVar.set(1)
            scrollToTop()
            blur()
end handleSearchInput

def scrollToTop(): Unit =
  val textElem = dom.document.getElementById("text")

  if textElem ne null then textElem.scrollTop = 0

def setMode(mode: Mode): Unit =
  modeVar set mode

  if mode == "dark" then dom.document.documentElement.classList.add("dark")
  else dom.document.documentElement.classList.remove("dark")
