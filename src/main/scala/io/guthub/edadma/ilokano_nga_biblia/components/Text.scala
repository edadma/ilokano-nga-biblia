package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLDivElement

def Text(modifiers: Modifier[ReactiveHtmlElement[HTMLDivElement]]*): ReactiveHtmlElement[HTMLDivElement] =
  div(
    cls := "font-normal font-sans text-md text-gray-600 dark:text-gray-400",
    modifiers,
  )
