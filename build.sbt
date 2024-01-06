import org.scalajs.jsenv.nodejs.NodeJSEnv

import scala.sys.process.Process

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
  .settings(
    name := "ilokano-nga-biblia",
    version := "0.0.1",
    scalaVersion := "3.3.1",
    organization := "io.github.edadma",
    githubOwner := "edadma",
    githubRepository := name.value,
    resolvers += Resolver.githubPackages("edadma"),
    libraryDependencies += "com.raquo" %%% "laminar" % "16.0.0",
    libraryDependencies += "io.github.edadma" %%% "translations" % "0.0.2",
    jsEnv := new NodeJSEnv(),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    externalNpm := {
      Process("npm", baseDirectory.value).!
      baseDirectory.value
    },
    stIgnore ++= List(
      "@capacitor/android",
      "@capacitor/cli",
//      "@capacitor/core",
    ),
  )
