package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*

import io.guthub.edadma.ilokano_nga_biblia.text.juan

val book = Var(juan.book)
val bookSignal = book.signal
val chapter = Var(1)
val chapterSignal = chapter.signal

def App =
  Card(
    div(
      prevNext,
      child <-- bookSignal
        .combineWith(chapterSignal)
        .map((b, c) => foreignHtmlElement(DomApi.unsafeParseHtmlString(b(c - 1)))),
      prevNext,
    ),
  )

def prevNext =
  div(
    cls := "flex justify-between",
    child <-- chapterSignal.map(ch => if ch > 1 then LefttButton(_ => chapter.update(_ - 1)) else div()),
    child <-- chapterSignal.map(ch =>
      if ch < bookSignal.now().length then RightButton(_ => chapter.update(_ + 1)) else div(),
    ),
  )
