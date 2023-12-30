package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom.HTMLInputElement

def Input(
    placeholderText: String = "",
    onChangeValue: String => Unit = _ => {},
    onChangeRef: HTMLInputElement => Unit = _ => {},
    clas: String = "",
) =
  input(
    typ := "text",
    cls := "bg-gray-50 border border-gray-300 text-gray-900 text-lg rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500",
    cls := clas,
    placeholder := placeholderText,
    onChange.mapToValue --> onChangeValue,
    inContext(thisNode => onChange --> { _ => onChangeRef(thisNode.ref) }),
  )
