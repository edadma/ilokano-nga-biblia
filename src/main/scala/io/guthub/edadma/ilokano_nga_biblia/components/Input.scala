package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}

def Input(placeholderText: String = "", onChangeEvent: String => Unit = _ => {}) =
  input(
    typ := "text",
    cls := "bg-gray-50 border border-gray-300 text-gray-900 text-lg rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500",
    placeholder := placeholderText,
    onChange.mapToValue --> onChangeEvent,
  )