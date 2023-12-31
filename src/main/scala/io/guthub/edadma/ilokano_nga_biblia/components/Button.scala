package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.MouseEvent

def Button(content: Node, clas: String = "", border: Boolean = true, onClickEvent: MouseEvent => Unit = _ => {}) =
  button(
    typ := "button",
    cls := s"text-blue-700 hover:bg-blue-700 hover:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-full text-sm p-2.5 text-center inline-flex items-center dark:text-blue-500 dark:hover:text-white dark:focus:ring-blue-800 dark:hover:bg-blue-500",
    cls.toggle("border border-blue-700 dark:border-blue-500") := border,
    cls := clas,
    content,
    onClick --> onClickEvent,
  )
