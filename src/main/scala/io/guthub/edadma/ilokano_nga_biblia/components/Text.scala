package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}

def Text(content: Node*) =
  span(
    cls := "font-normal text-gray-200 dark:text-gray-400",
    content,
  )
