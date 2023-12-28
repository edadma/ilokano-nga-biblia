package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.{*, given}

def Button(content: Node, border: Boolean = true) =
  button(
    `type` := "button",
    cls := s"text-blue-700 ${if border then "border border-blue-700" else ""} hover:bg-blue-700 hover:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-full text-sm p-2.5 text-center inline-flex items-center ${if border then "dark:border-blue-500" else ""} dark:text-blue-500 dark:hover:text-white dark:focus:ring-blue-800 dark:hover:bg-blue-500",
    content,
  )

def LefttButton = Button(
  svg.svg(
    svg.className := "w-4 h-4",
    svg.xmlns := "http://www.w3.org/2000/svg",
    svg.fill := "none",
    svg.viewBox := "0 0 14 10",
    svg.path(
      svg.stroke := "currentColor",
      svg.strokeLineCap := "round",
      svg.strokeLineJoin := "round",
      svg.strokeWidth := "2",
      svg.d := "M13,5 H1 M1,5 L5,1 M1,5 L5,9",
    ),
  ),
  border = false,
)

def RightButton = Button(
  svg.svg(
    svg.className := "w-4 h-4",
    svg.xmlns := "http://www.w3.org/2000/svg",
    svg.fill := "none",
    svg.viewBox := "0 0 14 10",
    svg.path(
      svg.stroke := "currentColor",
      svg.strokeLineCap := "round",
      svg.strokeLineJoin := "round",
      svg.strokeWidth := "2",
      svg.d := "M1 5h12m0 0L9 1m4 4L9 9",
    ),
  ),
  border = false,
)
