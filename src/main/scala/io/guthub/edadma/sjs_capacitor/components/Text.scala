package io.guthub.edadma.sjs_capacitor.components

import com.raquo.laminar.api.L.{*, given}

def Text(content: Node*) =
  span(
    cls := "font-normal text-gray-500 dark:text-gray-400",
    content,
  )
