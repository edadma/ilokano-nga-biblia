package io.guthub.edadma.ilokano_nga_biblia

import scala.util.matching.Regex

val bibleReference = raw"\s*((?:\d\s*)?[a-zA-Z\s*]*[a-zA-Z])(?:\s+(\d+)(?:\s*[.:,]\s*(\d+))?)?\s*".r
val repeatedSpaces = new Regex(raw"( )\1+")

def parseBibleReference(ref: String): Option[(String, String, String)] =
  ref match
    case bibleReference(book, chapter, verse) => Some(replaceRepeatedSpaces(book).toLowerCase, chapter, verse)
    case _                                    => None

def replaceRepeatedSpaces(input: String): String = repeatedSpaces.replaceAllIn(input, _ group 1)
