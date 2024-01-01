package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLDivElement

def Card(modifiers: Modifier[ReactiveHtmlElement[HTMLDivElement]]*): ReactiveHtmlElement[HTMLDivElement] =
  div(
    cls := "max-w-full p-6 bg-white border border-gray-200 shadow dark:bg-gray-800 dark:border-gray-700",
    modifiers,
  )
