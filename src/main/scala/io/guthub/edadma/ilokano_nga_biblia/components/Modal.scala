package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.HTMLDivElement

def Modal(
    modifiers: Modifier[ReactiveHtmlElement[HTMLDivElement]]*,
)(show: Var[Boolean], title: String): ReactiveHtmlElement[HTMLDivElement] =
  div(
    cls := "overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full",
    cls.toggle("hidden") <-- show.signal.map(!_),
    div(
      cls := "relative p-4 w-full max-w-2xl max-h-full",
      div(
        cls := "relative bg-white rounded-lg shadow dark:bg-gray-700",
        div(
          cls := "flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600",
          Text(cls := "text-xl font-semibold", title),
          button (typ:="button", cls:="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white",
  <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
  </svg>
    <span class="sr-only">Close modal
        ),
        modifiers,
      ),
    ),
  )
