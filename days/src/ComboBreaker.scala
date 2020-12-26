import scala.io.Source
import scala.annotation.tailrec

object ComboBreaker extends App {
  val cardPk +: doorPk +: _ = 
    Source.fromResource("day25.txt")
      .getLines()
      .map(_.toLong)
      .toSeq

  def iterate(subjectNumber: Long, value: Long) =
    value * subjectNumber % 20201227L

  def computeKey(subjectNumber: Long, loopSize: Int): Long = 
    LazyList.iterate(subjectNumber)(
      value => iterate(subjectNumber, value)
    )(loopSize -1)
     
  def crackLoopSize(subjectNumber: Long, publicKey: Long) =
    LazyList
      .iterate(subjectNumber)(value => iterate(subjectNumber, value))
      .zipWithIndex
      .collectFirst { case (key, loopSize) if key == publicKey => loopSize +1 }

  val cardLoopSize = crackLoopSize(7, cardPk).get
  println("part one: " + computeKey(doorPk, cardLoopSize))
}
