package io.guthub.edadma.ilokano_nga_biblia

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import text.*

@js.native @JSImport("/images/john-1.png", JSImport.Default)
val john1png: String = js.native
@js.native @JSImport("/images/john-2.png", JSImport.Default)
val john2png: String = js.native
@js.native @JSImport("/images/john-3.png", JSImport.Default)
val john3png: String = js.native
@js.native @JSImport("/images/john-4.png", JSImport.Default)
val john4png: String = js.native
@js.native @JSImport("/images/john-5.png", JSImport.Default)
val john5png: String = js.native

val imagesMap =
  Map[(Book, Int), String](
    (juan.book, 1) -> john1png,
    (juan.book, 2) -> john2png,
    (juan.book, 3) -> john3png,
    (juan.book, 4) -> john4png,
    (juan.book, 5) -> john5png,
  )
