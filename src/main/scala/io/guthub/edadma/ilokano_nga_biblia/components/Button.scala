package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLButtonElement

def Button(modifiers: Modifier[ReactiveHtmlElement[HTMLButtonElement]]*)(
    border: Boolean = false,
    rounded: Boolean = true,
    focus: Boolean = false,
): ReactiveHtmlElement[HTMLButtonElement] =
  button(
    typ := "button",
    cls := "bg-gray-500 text-blue-700 font-medium text-md p-2.5 text-center justify-center inline-flex items-center dark:bg-gray-800 dark:text-blue-400",
    //  hover:bg-gray-900
    cls.toggle("focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800") := focus,
    cls.toggle("rounded") := rounded,
    //  hover:bg-blue-600 hover:text-white dark:hover:text-white dark:hover:bg-blue-400
    cls.toggle("border border-blue-600 dark:border-blue-400") := border,
    modifiers,
  )

// text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700
