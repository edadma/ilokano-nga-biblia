package io.guthub.edadma.ilokano_nga_biblia

object FormatString:
  private val tagRegex = """\$([a-z]+)""".r

  def apply(book: Book, chapter: Int, data: Map[String, String]): String =
    val (text, _) = book(chapter - 1)

    tagRegex.replaceSomeIn(text, m => data get m.group(1))
