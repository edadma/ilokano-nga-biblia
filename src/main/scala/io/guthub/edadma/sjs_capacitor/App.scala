package io.guthub.edadma.sjs_capacitor

import com.raquo.laminar.DomApi
import com.raquo.laminar.api.L.{*, given}
import components.*
//import io.laminext.fetch._
//import concurrent.ExecutionContext.Implicits.global

import typings.capacitorCore.mod.CapacitorHttp.get
import typings.capacitorCore.typesCorePluginsMod.HttpOptions

def App =
//  div(
//    cls := "max-w-full p-6 bg-white border border-gray-200 shadow dark:bg-gray-800 dark:border-gray-700",
//    foreignHtmlElement(DomApi.unsafeParseHtmlString(text.juan1)),
//    child <-- EventStream
//      .fromJsPromise(get(HttpOptions("text/juan/1")))
//      .map(response => response.data.asInstanceOf[String])
//    child <-- Fetch
//      .get("text/juan/1")
//      .text
//      .data
//    child.text <-- FetchStream
//      .get("text/juan/1"),
//      .map(_.responseText)
//      .map(html => foreignHtmlElement(DomApi.unsafeParseHtmlString(html))),
//  )

  Card(
    foreignHtmlElement(DomApi.unsafeParseHtmlString(text.juan1)))
