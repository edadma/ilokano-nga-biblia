package io.guthub.edadma.ilokano_nga_biblia

val bibleReference = raw"\s*((?:\d\s*)?[a-zA-Z]+)(?:\s*(\d+)(?:\s*[.:,]\s*(\d+))?)?\s*".r

def parseBibleReference(ref: String): Option[(String, String, String)] =
  ref match
    case bibleReference(book, chapter, verse) => Some(book.replace(" ", "").toLowerCase, chapter, verse)
    case _                                    => None
