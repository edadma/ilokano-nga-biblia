package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLDivElement

def Modal(
    modifiers: Modifier[ReactiveHtmlElement[HTMLDivElement]]*,
)(show: Signal[Boolean]): ReactiveHtmlElement[HTMLDivElement] =
  div(
    cls := "overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full",
    cls.toggle("hidden") <-- show.map(!_),
    div(
      cls := "relative p-4 w-full max-w-2xl max-h-full",
      div(cls := "relative bg-white rounded-lg shadow dark:bg-gray-700", modifiers),
    ),
  )
