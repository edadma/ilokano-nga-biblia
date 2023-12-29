package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.svg.*
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom.SVGSVGElement

object SVG:
  def leftArrow: ReactiveSvgElement[SVGSVGElement] =
    svg(
      className := "w-4 h-4",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "none",
      viewBox := "0 0 14 10",
      path(
        stroke := "currentColor",
        strokeLineCap := "round",
        strokeLineJoin := "round",
        strokeWidth := "2",
        d := "M13,5 H1 M1,5 L5,1 M1,5 L5,9",
      ),
    )

  def rightArrow: ReactiveSvgElement[SVGSVGElement] =
    svg(
      className := "w-4 h-4",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "none",
      viewBox := "0 0 14 10",
      path(
        stroke := "currentColor",
        strokeLineCap := "round",
        strokeLineJoin := "round",
        strokeWidth := "2",
        d := "M1 5h12m0 0L9 1m4 4L9 9",
      ),
    )
