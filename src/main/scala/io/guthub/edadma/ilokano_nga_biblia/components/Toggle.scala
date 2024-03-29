package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.{HTMLInputElement, HTMLLabelElement}

def Toggle(modifiers: Modifier[ReactiveHtmlElement[HTMLInputElement]]*): ReactiveHtmlElement[HTMLLabelElement] =
  label(
    cls := "relative cursor-pointer",
    input(typ := "checkbox", value := "", cls := "peer sr-only", modifiers),
    div(
      cls := "peer h-6 w-11 rounded-full bg-gray-200 after:absolute after:start-[2px] after:top-[2px] after:h-5 after:w-5 after:rounded-full after:border after:border-gray-300 after:bg-white after:transition-all after:content-[''] peer-checked:bg-blue-600 peer-checked:after:translate-x-full peer-checked:after:border-white peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rtl:peer-checked:after:-translate-x-full dark:border-gray-600 dark:bg-gray-800 dark:peer-focus:ring-blue-800",
    ),
  )
