package io.guthub.edadma.ilokano_nga_biblia

import text.*

val booksOT =
  List(
    "Genesis" -> genesis.book,
    "Ruth" -> ruth.book,
  )
val booksNT =
  List(
    "Juan" -> juan.book,
    "Aramid" -> aramid.book,
    "Roma" -> roma.book,
    "1 Corinto" -> umuna_a_corinto.book,
    "2 Corinto" -> maikadua_a_corinto.book,
    "Galacia" -> galacia.book,
    "Efeso" -> efeso.book,
    "Filipos" -> filipos.book,
    "Colosas" -> colosas.book,
    "1 Tesalonica" -> umuna_a_tesalonica.book,
    "2 Tesalonica" -> maikadua_a_tesalonica.book,
    "1 Timoteo" -> umuna_a_timoteo.book,
    "2 Timoteo" -> maikadua_a_timoteo.book,
    "Tito" -> tito.book,
    "Filemon" -> filemon.book,
    "Hebreo" -> hebreo.book,
    "Santiago" -> santiago.book,
    "1 Pedro" -> umuna_a_pedro.book,
    "2 Pedro" -> maikadua_a_pedro.book,
    "1 Juan" -> umuna_a_juan.book,
    "2 Juan" -> maikadua_a_juan.book,
    "3 Juan" -> maikatlo_a_juan.book,
    "Judas" -> judas.book,
    "Apocalipsis" -> apocalipsis.book,
  )
val booksMap =
  Map(
    // Genesis
    "genesis" -> genesis.book,
    "ge" -> genesis.book,
    "gen" -> genesis.book,

    // Ruth
    "ruth" -> ruth.book,
    "ru" -> ruth.book,
    "rut" -> ruth.book,

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

    // 1 Corinthians
    "1corinto" -> umuna_a_corinto.book,
    "1cor" -> umuna_a_corinto.book,
    "1co" -> umuna_a_corinto.book,

    // 2 Corinthians
    "2corinto" -> maikadua_a_corinto.book,
    "2cor" -> maikadua_a_corinto.book,
    "2co" -> maikadua_a_corinto.book,

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
    "1tesalonica" -> umuna_a_tesalonica.book,
    "1tes" -> umuna_a_tesalonica.book,
    "1ts" -> umuna_a_tesalonica.book,

    // 2 Thessalonians
    "2tesalonica" -> maikadua_a_tesalonica.book,
    "2tes" -> maikadua_a_tesalonica.book,
    "2ts" -> maikadua_a_tesalonica.book,

    // 1 Timothy
    "1timoteo" -> umuna_a_timoteo.book,
    "1tim" -> umuna_a_timoteo.book,
    "1tm" -> umuna_a_timoteo.book,

    // 2 Timothy
    "2timoteo" -> maikadua_a_timoteo.book,
    "2tim" -> maikadua_a_timoteo.book,
    "2tm" -> maikadua_a_timoteo.book,

    // Titus
    "tito" -> tito.book,
    "tit" -> tito.book,
    "ti" -> tito.book,

    // Philemon
    "filemon" -> filemon.book,
    "fil" -> filemon.book,
    "fi" -> filemon.book,

    // Hebrews
    "hebreo" -> hebreo.book,
    "heb" -> hebreo.book,
    "he" -> hebreo.book,

    // James
    "santiago" -> santiago.book,
    "san" -> santiago.book,
    "sa" -> santiago.book,

    // 1 Peter
    "1pedro" -> umuna_a_pedro.book,
    "1ped" -> umuna_a_pedro.book,
    "1pe" -> umuna_a_pedro.book,

    // 2 Peter
    "2pedro" -> umuna_a_pedro.book,
    "2ped" -> umuna_a_pedro.book,
    "2pe" -> umuna_a_pedro.book,

    // 1 John
    "1juan" -> umuna_a_juan.book,
    "1jn" -> umuna_a_juan.book,
    "1jua" -> umuna_a_juan.book,

    // 2 John
    "2juan" -> maikadua_a_juan.book,
    "2jn" -> maikadua_a_juan.book,
    "2jua" -> maikadua_a_juan.book,

    // 3 John
    "3juan" -> maikatlo_a_juan.book,
    "3jn" -> maikatlo_a_juan.book,
    "3jua" -> maikatlo_a_juan.book,

    // Jude
    "judas" -> judas.book,
    "jud" -> judas.book,
    "jd" -> judas.book,

    // Revelation
    "apocalipsis" -> apocalipsis.book,
    "apo" -> apocalipsis.book,
    "ap" -> apocalipsis.book,
  )
