package day2

import scala.io.Source
import scala.util.Try
import scala.util.Success

object PasswordPhilosophy extends App {
  val inputPattern = raw"(\d+)-(\d+) (\w): (\w+)".r
  val input = Source
    .fromResource("/day2.txt")
    .getLines
    .map { case inputPattern(lo, hi, char, pw) => (lo.toInt, hi.toInt, char, pw) }
    .toSeq

    println(input)
}
