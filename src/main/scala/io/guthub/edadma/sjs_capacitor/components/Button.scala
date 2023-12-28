package io.guthub.edadma.sjs_capacitor.components

import com.raquo.laminar.api.L.{*, given}

def Button(content: Node*) =
  button(
    `type` := "button",
    cls := "text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-full text-sm p-2.5 text-center inline-flex items-center me-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800",
    content,
  )
