package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*
import org.scalajs.dom

import io.guthub.edadma.ilokano_nga_biblia.text.juan

val bookVar = Var(juan.book)
val bookSignal = bookVar.signal
val chapterVar = Var(1)
val chapterSignal = chapterVar.signal

def App =
  Card(
    div(
      cls := "overflow-hidden",
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
                          bookVar.update(_ => b)
                          chapterVar.update(_ => chap)
                        end if
                      else
                        bookVar.update(_ => b)
                        chapterVar.update(_ => chap)
                        scrollToTop()
                      end if
                    end if
                  else
                    bookVar.update(_ => b)
                    chapterVar.update(_ => 1)
                    scrollToTop()
        },
        onChangeRef = ref => ref.blur(),
      ),
      div(
        cls := "flex justify-between",
        child <-- chapterSignal.map(ch =>
          if ch > 1 then Button(SVG.leftArrow, border = false, _ => { chapterVar.update(_ - 1); scrollToTop() })
          else div(),
        ),
        child <-- chapterSignal.map(ch =>
          if ch < bookSignal.now().length then
            Button(SVG.rightArrow, border = false, _ => { chapterVar.update(_ + 1); scrollToTop() })
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

def scrollToTop(): Unit =
  val textElem = dom.document.getElementById("text")

  if textElem ne null then textElem.scrollTop = 0
