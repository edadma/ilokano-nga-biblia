package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*
import org.scalajs.dom

import scala.scalajs.js.Thenable.Implicits._
import concurrent.ExecutionContext.Implicits.global

import typings.capacitorPreferences.mod.Preferences
import typings.capacitorPreferences.distEsmDefinitionsMod.{SetOptions, GetOptions, RemoveOptions}

import io.guthub.edadma.ilokano_nga_biblia.text.juan

import scala.util.Random

type Mode = "light" | "dark"

val bookVar = Var(juan.book)
val bookSignal = bookVar.signal
val chapterVar = Var(1)
val chapterSignal = chapterVar.signal
val modeVar = Var[Mode]("light")
val modeSignal = modeVar.signal

def App =
  Preferences.get(GetOptions("mode")) foreach { v =>
    v.value match
      case null                      =>
      case mode @ ("light" | "dark") => setMode(mode)
      case _                         => Preferences.remove(RemoveOptions("mode"))
  }

  Card(
    div(
      cls := "overflow-hidden",
      div(
        cls := "flex justify-between",
        Input(
          placeholderText = "Sapulen",
          clas = " sm:max-w-md",
          onChangeValue = x => {
            parseBibleReference(x) match
              case None =>
              case Some(book, chapter, verse) =>
                books get book match
                  case None =>
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
                          end if
                        else
                          bookVar.set(b)
                          chapterVar.set(chap)
                          scrollToTop()
                        end if
                      end if
                    else
                      bookVar.set(b)
                      chapterVar.set(1)
                      scrollToTop()
          },
          onChangeRef = ref =>
            ref.blur()
            ref.value = "",
        ),
        child <-- modeSignal.map(mode =>
          Button(
            if mode == "light" then SVG.moon else SVG.sun,
            border = false,
            clas = "ml-2",
            onClickEvent = _ => {
              val newMode: Mode = if mode == "light" then "dark" else "light"

              Preferences set SetOptions("mode", newMode)
              setMode(newMode)
            },
          ),
        ),
      ),
      div(
        cls := "flex justify-between",
        child <-- chapterSignal.map(ch =>
          if ch > 1 then
            Button(
              SVG.leftArrow,
              border = false,
              _ =>
                chapterVar.update(_ - 1)
                scrollToTop(),
            )
          else div(),
        ),
        child <-- chapterSignal.map(ch =>
          if ch < bookSignal.now().length then
            Button(
              SVG.rightArrow,
              border = false,
              _ =>
                chapterVar.update(_ + 1)
                scrollToTop(),
            )
          else div(),
        ),
      ),
      div(
        idAttr := "text",
        cls := "no-scrollbar overflow-auto h-[calc(100vh-135px)]",
        child <-- bookSignal
          .combineWith(chapterSignal)
          .map((book, chapter) => foreignHtmlElement(DomApi.unsafeParseHtmlString(book(chapter - 1)))),
      ),
    ),
  )
end App

def scrollToTop(): Unit =
  val textElem = dom.document.getElementById("text")

  if textElem ne null then textElem.scrollTop = 0

def setMode(mode: Mode): Unit =
  modeVar set mode

  if mode == "dark" then dom.document.documentElement.classList.add("dark")
  else dom.document.documentElement.classList.remove("dark")
