package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLInputElement

def Input(modifiers: Modifier[ReactiveHtmlElement[HTMLInputElement]]*): ReactiveHtmlElement[HTMLInputElement] =
  input(
    cls := "font-sans bg-gray-50 border border-gray-300 text-gray-900 text-md rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500",
    modifiers,
  )
