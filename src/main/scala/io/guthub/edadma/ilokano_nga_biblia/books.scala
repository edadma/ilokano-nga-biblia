package io.guthub.edadma.ilokano_nga_biblia

import text.{juan, umuna_a_juan}

val books =
  Map(
    // John
    "juan" -> juan.book,
    "jn" -> juan.book,
    "jua" -> juan.book,

    // 1 John
    "1 juan" -> umuna_a_juan.book,
    "1 jn" -> umuna_a_juan.book,
    "1 jua" -> umuna_a_juan.book,
  )
