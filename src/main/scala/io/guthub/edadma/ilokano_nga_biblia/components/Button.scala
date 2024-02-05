package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLButtonElement

def Button(modifiers: Modifier[ReactiveHtmlElement[HTMLButtonElement]]*): ReactiveHtmlElement[HTMLButtonElement] =
  button(
    typ := "button",
    cls :=
      """text-blue-700 font-medium rounded-lg
        |border border-blue-700 dark:text-blue-500
        |hover:text-white hover:bg-blue-800 dark:hover:text-white dark:hover:bg-blue-500
        |focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800
        |text-center
        |dark:border-blue-500
        |""".stripMargin,
    modifiers,
  )
