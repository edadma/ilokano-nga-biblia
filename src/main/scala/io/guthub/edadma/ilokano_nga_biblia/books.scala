package io.guthub.edadma.ilokano_nga_biblia

import text.*

val books =
  List(
    "Juan" -> juan.book,
    "1 Juan" -> umuna_a_juan.book,
    "2 Juan" -> maikadua_a_juan.book,
    "3 Juan" -> maikatlo_a_juan.book,
    "Judas" -> judas.book,
  )
val booksMap =
  Map(
    // John
    "juan" -> juan.book,
    "jn" -> juan.book,
    "jua" -> juan.book,

    // 1 John
    "1 juan" -> umuna_a_juan.book,
    "1 jn" -> umuna_a_juan.book,
    "1 jua" -> umuna_a_juan.book,

    // 2 John
    "2 juan" -> maikadua_a_juan.book,
    "2 jn" -> maikadua_a_juan.book,
    "2 jua" -> maikadua_a_juan.book,

    // 3 John
    "3 juan" -> maikatlo_a_juan.book,
    "3 jn" -> maikatlo_a_juan.book,
    "3 jua" -> maikatlo_a_juan.book,

    // Jude
    "judas" -> judas.book,
    "jud" -> judas.book,
    "jd" -> judas.book,
  )
