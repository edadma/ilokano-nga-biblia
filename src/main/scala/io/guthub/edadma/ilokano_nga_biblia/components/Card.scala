package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}

def Card(content: Node*) =
  div(
    cls := "max-w-full p-6 bg-white border border-gray-200 shadow dark:bg-gray-800 dark:border-gray-700",
    content,
  )
