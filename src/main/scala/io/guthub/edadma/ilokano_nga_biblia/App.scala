package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*

val book = Var(juan)
val bookSignal = book.signal
val chapter = Var(1)
val chapterSignal = chapter.signal

def App =
  Card(prevNext, foreignHtmlElement(DomApi.unsafeParseHtmlString(text.juan1)), prevNext)

def prevNext =
  div(
    cls := "flex justify-between",
    child <-- chapterSignal.map(ch => if ch > 1 then LefttButton else div()),
    child <-- chapterSignal.map(ch => if ch < bookSignal.now().length then RightButton else div()),
  )
