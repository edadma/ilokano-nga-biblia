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
                          verseElem.scrollIntoView()
                          bookVar.update(_ => b)
                          chapterVar.update(_ => chap)
                        end if
                      else
                        bookVar.update(_ => b)
                        chapterVar.update(_ => chap)
                      end if
                    end if
                  else bookVar.update(_ => b)
        },
        onChangeRef = ref => ref.blur(),
      ),
      div(
        cls := "flex justify-between",
        child <-- chapterSignal.map(ch => if ch > 1 then LefttButton(_ => chapterVar.update(_ - 1)) else div()),
        child <-- chapterSignal.map(ch =>
          if ch < bookSignal.now().length then RightButton(_ => chapterVar.update(_ + 1)) else div(),
        ),
      ),
      div(
        cls := "no-scrollbar overflow-auto h-[85.8vh]",
        child <-- bookSignal
          .combineWith(chapterSignal)
          .map((book, chapter) => foreignHtmlElement(DomApi.unsafeParseHtmlString(book(chapter - 1)))),
      ),
    ),
  )
