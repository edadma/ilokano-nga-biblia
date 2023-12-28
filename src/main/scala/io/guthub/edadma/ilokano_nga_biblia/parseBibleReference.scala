package io.guthub.edadma.ilokano_nga_biblia

val bibleReference = raw"\s*((?:\d\s*)?[a-zA-Z\s*]*[a-zA-Z])\s+(\d+)\s*[.:,]\s*(\d+)\s*".r

def parsseBibleReference(ref: String): Option[(String, Int, String)] =
  ref match
    case bibleReference(book, chapter, verse) => Some(book.toLowerCase, chapter.toInt, verse)
    case _                                    => None
