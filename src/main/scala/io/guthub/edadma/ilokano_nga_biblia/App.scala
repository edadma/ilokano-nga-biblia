package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*

import io.guthub.edadma.ilokano_nga_biblia.text.juan

val bookVar = Var(juan.book)
val bookSignal = bookVar.signal
val chapterVar = Var(1)
val chapterSignal = chapterVar.signal

def App =
  Card(
    Input(
      "Sapulen",
      x => println(x),
    ),
    prevNext,
    div(
      child <-- bookSignal
        .combineWith(chapterSignal)
        .map((book, chapter) => foreignHtmlElement(DomApi.unsafeParseHtmlString(book(chapter - 1)))),
    ),
    prevNext,
  )

def prevNext =
  div(
    cls := "flex justify-between",
    child <-- chapterSignal.map(ch => if ch > 1 then LefttButton(_ => chapterVar.update(_ - 1)) else div()),
    child <-- chapterSignal.map(ch =>
      if ch < bookSignal.now().length then RightButton(_ => chapterVar.update(_ + 1)) else div(),
    ),
  )
