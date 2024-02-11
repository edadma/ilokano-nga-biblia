package io.guthub.edadma.ilokano_nga_biblia

import text.*

val booksOT =
  List(
    "Genesis" -> genesis.book,
  )
val booksNT =
  List(
    "Juan" -> juan.book,
    "Aramid" -> aramid.book,
    "Roma" -> roma.book,
    "Galacia" -> galacia.book,
    "Efeso" -> efeso.book,
    "Filipos" -> filipos.book,
    "Colosas" -> colosas.book,
    "1 Tesalonica" -> umuna_a_tesalonica.book,
    "2 Tesalonica" -> maikadua_a_tesalonica.book,
    "1 Juan" -> umuna_a_juan.book,
    "2 Juan" -> maikadua_a_juan.book,
    "3 Juan" -> maikatlo_a_juan.book,
    "Judas" -> judas.book,
  )
val booksMap =
  Map(
    // Genesis
    "genesis" -> genesis.book,
    "ge" -> genesis.book,
    "gen" -> genesis.book,

    // John
    "juan" -> juan.book,
    "jn" -> juan.book,
    "jua" -> juan.book,

    // Acts
    "aramid" -> aramid.book,
    "ar" -> aramid.book,
    "ara" -> aramid.book,

    // Romans
    "romano" -> roma.book,
    "roma" -> roma.book,
    "rom" -> roma.book,
    "rm" -> roma.book,

    // Galatians
    "galacia" -> galacia.book,
    "gal" -> galacia.book,
    "gl" -> galacia.book,

    // Ephesians
    "efeso" -> efeso.book,
    "efe" -> efeso.book,
    "ef" -> efeso.book,

    // Philippians
    "filipos" -> filipos.book,
    "fil" -> filipos.book,
    "fl" -> filipos.book,

    // Colossians
    "colosas" -> colosas.book,
    "col" -> colosas.book,
    "cl" -> colosas.book,

    // 1 Thessalonians
    "1 tesalonica" -> umuna_a_tesalonica.book,
    "1 tes" -> umuna_a_tesalonica.book,
    "1 ts" -> umuna_a_tesalonica.book,

    // 2 Thessalonians
    "2 tesalonica" -> maikadua_a_tesalonica.book,
    "2 tes" -> maikadua_a_tesalonica.book,
    "2 ts" -> maikadua_a_tesalonica.book,

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
