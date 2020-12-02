package day1

import scala.io.Source
import scala.util.Success
import scala.util.Try

object ReportRepair extends App {
  // lol n^2, not going to bother with sorting
  def expenses2(entries: Seq[Int]) = 
    for {
      e1 <- entries
      e2 <- entries
      solution = e1 * e2 if e1 + e2 == 2020
    } yield solution

  val input = Source
    .fromResource("/day1.txt")
    .getLines
    .map(line => Try(line.toInt))
    .collect { case Success(entry) => entry }
    .toSeq

  // lol, why compute one solution when you can compute the same solution twice?
  println(expenses2(input))

  // ok, I have SOME dignity
  // but not today
  /*
  def expenses3(entries: Seq[Int]) = 
    for {
      e1 <- entries
      e2 <- entries if e1 + e2 < 2020
      e3 <- entries
      solution = e1 * e2 * e3 if e1 + e2 + e3 == 2020
    } yield solution
  */

  implicit class SeqSyntax[A](val seq: Seq[A]) extends AnyVal {
    def firstOpt[B](f: A => Option[B]) = seq.collectFirst(Function.unlift(f))

    def collectFirst3[B](f: PartialFunction[(A, A, A), B]) = 
      seq.firstOpt(a =>
      seq.firstOpt(b => 
      seq.firstOpt(c => f.lift(a, b, c)
    )))
  }

  def expenses3(entries: Seq[Int]) = 
    entries.collectFirst3 { 
      case (e1, e2, e3) if e1 + e2 + e3 == 2020 => e1 * e2 * e3
    }
  
  println(expenses3(input))
}
