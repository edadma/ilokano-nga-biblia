package io.guthub.edadma.ilokano_nga_biblia.components

import com.raquo.laminar.api.L.svg.*
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom.SVGSVGElement

object SVG:
  def cross: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-3 h-3",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "none",
      viewBox := "0 0 14 14",
      path(
        stroke := "currentColor",
        strokeLineCap := "round",
        strokeLineJoin := "round",
        strokeWidth := "2",
        d := "m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6",
      ),
    )

  def search: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "none",
      viewBox := "0 0 20 20",
      path(
        stroke := "currentColor",
        strokeLineCap := "round",
        strokeLineJoin := "round",
        strokeWidth := "2",
        d := "m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z",
      ),
    )

  //    <svg class="w-[16px] h-[16px] text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
//      <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
//    </svg>

  def about: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "currentColor",
      viewBox := "0 0 20 20",
      path(
        d := "M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z",
      ),
    )

  def userSettings: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "currentColor",
      viewBox := "0 0 20 19",
      path(
        d := "M7.324 9.917A2.479 2.479 0 0 1 7.99 7.7l.71-.71a2.484 2.484 0 0 1 2.222-.688 4.538 4.538 0 1 0-3.6 3.615h.002ZM7.99 18.3a2.5 2.5 0 0 1-.6-2.564A2.5 2.5 0 0 1 6 13.5v-1c.005-.544.19-1.072.526-1.5H5a5.006 5.006 0 0 0-5 5v2a1 1 0 0 0 1 1h7.687l-.697-.7ZM19.5 12h-1.12a4.441 4.441 0 0 0-.579-1.387l.8-.795a.5.5 0 0 0 0-.707l-.707-.707a.5.5 0 0 0-.707 0l-.795.8A4.443 4.443 0 0 0 15 8.62V7.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.12c-.492.113-.96.309-1.387.579l-.795-.795a.5.5 0 0 0-.707 0l-.707.707a.5.5 0 0 0 0 .707l.8.8c-.272.424-.47.891-.584 1.382H8.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1.12c.113.492.309.96.579 1.387l-.795.795a.5.5 0 0 0 0 .707l.707.707a.5.5 0 0 0 .707 0l.8-.8c.424.272.892.47 1.382.584v1.12a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1.12c.492-.113.96-.309 1.387-.579l.795.8a.5.5 0 0 0 .707 0l.707-.707a.5.5 0 0 0 0-.707l-.8-.795c.273-.427.47-.898.584-1.392h1.12a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5ZM14 15.5a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5Z",
      ),
    )

  def leftArrow: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "none",
      viewBox := "0 0 8 14",
      path(
        stroke := "currentColor",
        strokeLineCap := "round",
        strokeLineJoin := "round",
        strokeWidth := "2",
        d := "M7 1 1.3 6.326a.91.91 0 0 0 0 1.348L7 13",
      ),
    )

  def rightArrow: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "none",
      viewBox := "0 0 8 14",
      path(
        stroke := "currentColor",
        strokeLineCap := "round",
        strokeLineJoin := "round",
        strokeWidth := "2",
        d := "m1 13 5.7-5.326a.909.909 0 0 0 0-1.348L1 1",
      ),
    )

  def sun: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "currentColor",
      viewBox := "0 0 20 20",
      path(
        d := "M10 15a5 5 0 1 0 0-10 5 5 0 0 0 0 10Zm0-11a1 1 0 0 0 1-1V1a1 1 0 0 0-2 0v2a1 1 0 0 0 1 1Zm0 12a1 1 0 0 0-1 1v2a1 1 0 1 0 2 0v-2a1 1 0 0 0-1-1ZM4.343 5.757a1 1 0 0 0 1.414-1.414L4.343 2.929a1 1 0 0 0-1.414 1.414l1.414 1.414Zm11.314 8.486a1 1 0 0 0-1.414 1.414l1.414 1.414a1 1 0 0 0 1.414-1.414l-1.414-1.414ZM4 10a1 1 0 0 0-1-1H1a1 1 0 0 0 0 2h2a1 1 0 0 0 1-1Zm15-1h-2a1 1 0 1 0 0 2h2a1 1 0 0 0 0-2ZM4.343 14.243l-1.414 1.414a1 1 0 1 0 1.414 1.414l1.414-1.414a1 1 0 0 0-1.414-1.414ZM14.95 6.05a1 1 0 0 0 .707-.293l1.414-1.414a1 1 0 1 0-1.414-1.414l-1.414 1.414a1 1 0 0 0 .707 1.707Z",
      ),
    )

  def moon: ReactiveSvgElement[SVGSVGElement] =
    svg(
      cls := "w-[16px] h-[16px]",
      xmlns := "http://www.w3.org/2000/svg",
      fill := "currentColor",
      viewBox := "0 0 18 20",
      path(
        d := "M17.8 13.75a1 1 0 0 0-.859-.5A7.488 7.488 0 0 1 10.52 2a1 1 0 0 0 0-.969A1.035 1.035 0 0 0 9.687.5h-.113a9.5 9.5 0 1 0 8.222 14.247 1 1 0 0 0 .004-.997Z",
      ),
    )
