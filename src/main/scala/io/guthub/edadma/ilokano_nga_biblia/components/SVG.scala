package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.svg.*
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom.SVGSVGElement

object SVG:
  def leftArrow: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-4 h-4",
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
      cls := "w-4 h-4",
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

  def sun: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px] text-gray-600 dark:text-gray-400",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "currentColor",
      viewBox := "0 0 20 20",
      path(
        d := "M10 15a5 5 0 1 0 0-10 5 5 0 0 0 0 10Zm0-11a1 1 0 0 0 1-1V1a1 1 0 0 0-2 0v2a1 1 0 0 0 1 1Zm0 12a1 1 0 0 0-1 1v2a1 1 0 1 0 2 0v-2a1 1 0 0 0-1-1ZM4.343 5.757a1 1 0 0 0 1.414-1.414L4.343 2.929a1 1 0 0 0-1.414 1.414l1.414 1.414Zm11.314 8.486a1 1 0 0 0-1.414 1.414l1.414 1.414a1 1 0 0 0 1.414-1.414l-1.414-1.414ZM4 10a1 1 0 0 0-1-1H1a1 1 0 0 0 0 2h2a1 1 0 0 0 1-1Zm15-1h-2a1 1 0 1 0 0 2h2a1 1 0 0 0 0-2ZM4.343 14.243l-1.414 1.414a1 1 0 1 0 1.414 1.414l1.414-1.414a1 1 0 0 0-1.414-1.414ZM14.95 6.05a1 1 0 0 0 .707-.293l1.414-1.414a1 1 0 1 0-1.414-1.414l-1.414 1.414a1 1 0 0 0 .707 1.707Z",
      ),
    )

  def moon: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px] text-gray-600 dark:text-gray-400",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "currentColor",
      viewBox := "0 0 18 20",
      path(
        d := "M17.8 13.75a1 1 0 0 0-.859-.5A7.488 7.488 0 0 1 10.52 2a1 1 0 0 0 0-.969A1.035 1.035 0 0 0 9.687.5h-.113a9.5 9.5 0 1 0 8.222 14.247 1 1 0 0 0 .004-.997Z",
      ),
    )
