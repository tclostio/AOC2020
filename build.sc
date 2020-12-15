import mill._, scalalib._

object days extends ScalaModule {
  def scalaVersion = "2.13.4"
  def scalacOptions = Seq(
    "-Xfatal-warnings", "-feature", "-unchecked","-deprecation",
    "-Ywarn-macros:after"
  )
}
