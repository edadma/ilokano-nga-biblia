package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLButtonElement

def Button(modifiers: Modifier[ReactiveHtmlElement[HTMLButtonElement]]*): ReactiveHtmlElement[HTMLButtonElement] =
  button(
    typ := "button",
    cls := s"text-blue-600 hover:bg-blue-600 hover:text-white font-medium rounded-full text-md p-2.5 text-center inline-flex items-center dark:text-blue-400 dark:hover:text-white dark:hover:bg-blue-400",
    // focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800
//    cls.toggle("border border-blue-600 dark:border-blue-400") := border,
    modifiers,
  )
