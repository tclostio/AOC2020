import helpers._
import scala.io.Source
import scala.annotation.tailrec

object EncodingError extends App {
  def input = 
    Source.fromResource("day9.txt")
      .getLines()
      .map(_.toLong)

  def findFirstInvalid(preamble: Int, nums: Seq[Long]) = {
    def hasMatchingPair(num: Long, i: Int) = 
      nums
        .drop(i) // only include the preample-length numbers before this one
        .take(preamble)
        .collectFirst2 { case (a, b) if a + b == num => () }
        .isDefined

    nums
      .drop(preamble) // skip the preamble
      .zipWithIndex
      .collectFirst { case (num, i) if !hasMatchingPair(num, i) => num }
  }

  lazy val firstInvalidNumber = findFirstInvalid(25, input.toList).get

  println(s"part one: $firstInvalidNumber")

  case class Acc(min: Long = Long.MaxValue, max: Long = 0, sum: Long = 0) {
    def :+(num: Long) = Acc(
      math.min(min, num), 
      math.max(max, num), 
      sum + num
    )
  }

  @tailrec
  def findWeakness(invalidNum: Long, nums: Seq[Long]): Option[Long] = {
    val run = nums.iterator.foldWhile(Acc()) {
      case (acc, num) if acc.sum + num <= invalidNum => acc :+ num
    }

    if(run.sum == invalidNum) Some(run.min + run.max)
    else nums.tail match {
      case Seq() => None
      case tail  => findWeakness(invalidNum, tail)
    }
  }
  
  println("part two: " + findWeakness(firstInvalidNumber, LazyList.from(input)))
}
