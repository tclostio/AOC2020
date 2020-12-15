import scala.io.Source
import scala.annotation.tailrec

object BinaryBoarding extends App {
  val seatLayout = Seq.fill(128)(0 to 7).zipWithIndex

  def input = Source.fromResource("day5.txt").getLines()

  @tailrec
  def computeSeatCoordinates(
    seat: String, 
    layout: Seq[(Seq[Int], Int)] = seatLayout
  ): (Int, Int) = seat match {
    case s"F$tail" => computeSeatCoordinates(tail, layout.take(layout.length / 2))
    case s"B$tail" => computeSeatCoordinates(tail, layout.drop(layout.length / 2))

    case s"L$tail" => computeSeatCoordinates(tail, layout.map { 
      case (column, row) => (column.take(column.length / 2), row) 
    })

    case s"R$tail" => computeSeatCoordinates(tail, layout.map { 
      case (column, row) => (column.drop(column.length / 2), row) 
    })

    case "" => 
      val (column +: _, row) +: _ = layout
      (row, column)
  }

  def computeSeatId(row: Int, column: Int): Long = row * 8 + column

  val highestSeatId = 
    input
    .map(computeSeatCoordinates(_))
    .map((computeSeatId _).tupled)
    .max

  println(s"part one: $highestSeatId")

  def findMySeat = {
    val seatIds = input
      .map(computeSeatCoordinates(_))
      .map((computeSeatId _).tupled)
      .toSeq
      .sorted

    val (oneLower, _) = seatIds
      .zip(seatIds.tail)
      .find { case (lo, hi) => hi - lo != 1 }
      .get

    oneLower + 1
  }

  println(s"part two: $findMySeat")
}
