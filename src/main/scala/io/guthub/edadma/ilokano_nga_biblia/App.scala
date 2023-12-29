package io.guthub.edadma.ilokano_nga_biblia

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*

val book = Var(juan)
val chapter = Var(1)

def App =
  Card(prevNext, foreignHtmlElement(DomApi.unsafeParseHtmlString(text.juan1)), prevNext)

def prevNext(book: Book, chapter: Int) = div(cls := "flex justify-between", LefttButton, RightButton)
