package io.guthub.edadma.ilokano_nga_biblia

import text.{juan, maysa_juan}

val books =
  Map(
    // John
    "juan" -> juan.book,
    "jn" -> juan.book,
    "jua" -> juan.book,

    // 1 John
    "1 juan" -> maysa_juan.book,
    "1 jn" -> maysa_juan.book,
    "1 jua" -> maysa_juan.book,
  )
