package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLButtonElement

def Button(modifiers: Modifier[ReactiveHtmlElement[HTMLButtonElement]]*)(
    border: Boolean = false,
    focus: Boolean = false,
    rounded: Boolean = true,
): ReactiveHtmlElement[HTMLButtonElement] =
  button(
    typ := "button",
    cls := s" bg-gray-800 hover:bg-gray-900 text-blue-600 font-sans font-medium text-md p-2.5 text-center justify-center inline-flex items-center dark:text-blue-400",
    cls.toggle("rounded") := rounded,
    cls.toggle(
      "focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 hover:bg-blue-600 hover:text-white dark:hover:text-white dark:hover:bg-blue-400",
    ) := focus,
    cls.toggle("border border-blue-600 dark:border-blue-400") := border,
    modifiers,
  )
