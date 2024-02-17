package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLButtonElement

def Clickable(modifiers: Modifier[ReactiveHtmlElement[HTMLButtonElement]]*)(
    border: Boolean = false,
    rounded: Boolean = true,
    focus: Boolean = false,
): ReactiveHtmlElement[HTMLButtonElement] =
  button(
    typ := "button",
    cls := "text-blue-600 font-sans text-sm text-center justify-center inline-flex items-center dark:text-blue-400",
    modifiers,
  )
