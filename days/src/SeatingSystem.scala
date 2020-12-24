import scala.io.Source
import helpers._
import scala.language.postfixOps

// seats are Some, floor is None
case class Room(seats: Seq[Seq[Option[Boolean]]]) {
  def spot(x: Int, y: Int) = 
    if(seats.isDefinedAt(y) && seats(y).isDefinedAt(x)) Some(Spot(x, y, this))
    else None

  def withRulesApplied(newRules: Boolean = false) = {
    val newSeats = 
      (0 until seats.length).map(y =>
        (0 until seats.head.length).map { x => 
          val spot = Spot(x, y, this)
          if(spot.floor) None
          else if(spot.willVacate(newRules)) Some(false)
          else if(spot.willOccupy(newRules)) Some(true)
          else spot.raw
        }
      )
    Room(newSeats)
  }

  def countOccupiedSeats = seats.map(_.count(_.contains(true))).sum

  override def toString = seats.map(row => row.map {
    case None        => '.'
    case Some(true)  => '#'
    case Some(false) => 'L'
  }.mkString).mkString("\n") 
}

case class Spot(x: Int, y: Int, room: Room) {
  def raw      = room.seats(y)(x)
  def occupied = room.seats(y)(x).contains(true)
  def floor    = room.seats(y)(x).isEmpty

  def ⬉ = room.spot(x -1, y -1)
  def ⬆ = room.spot(x   , y -1)
  def ⬈ = room.spot(x +1, y -1)
  def ➡ = room.spot(x +1, y   )
  def ⬊ = room.spot(x +1, y +1)
  def ⬇ = room.spot(x   , y +1)
  def ⬋ = room.spot(x -1, y +1)
  def ⬅ = room.spot(x -1, y   )

  // find the first occupid spot in a direction, if one exists
  private def iterateDirection(move: Spot => Option[Spot]) = 
    LazyList
      .iterate(move(this))(_.flatMap(move(_)))                 // keep moving in the given direction
      .takeWhile(_.isDefined)                                  // until you hit a wall
      .collectFirst { case Some(spot) if !spot.floor => spot } // or you hit a spot with a person in it

  def ⇖ = iterateDirection(_ ⬉)
  def ⇑ = iterateDirection(_ ⬆)
  def ⇗ = iterateDirection(_ ⬈)
  def ⇏ = iterateDirection(_ ➡)
  def ⇘ = iterateDirection(_ ⬊)
  def ⇓ = iterateDirection(_ ⬇)
  def ⇙ = iterateDirection(_ ⬋)
  def ⇐ = iterateDirection(_ ⬅)

  def adjacentSeats(newRules: Boolean) = (
    if(newRules) Seq(⇖, ⇑, ⇗, ⇏, ⇘, ⇓, ⇙, ⇐)
    else         Seq(⬉, ⬆, ⬈, ➡, ⬊, ⬇, ⬋, ⬅)
  ).flatten

  def willOccupy(newRules: Boolean) = 
    !occupied && !adjacentSeats(newRules).exists(_.occupied)

  def willVacate(newRules: Boolean) = {
    val threshold = if(newRules) 5 else 4

    occupied && adjacentSeats(newRules).count(_.occupied) >= threshold
  }
}

object SeatingSystem extends App {
  def input = Source.fromResource("day11.txt")
    .getLines()  
    .map(_.map {
      case 'L' => Some(false)
      case '.' => None
    })

  val startingRoom = Room(input.toIndexedSeq)

  def findAttractor(room: Room, newRules: Boolean = false) = 
    LazyList
      .iterate(room)(_.withRulesApplied(newRules))
      .tail
      .foldWhile(room) { case (old, _new) 
        if old.seats != _new.seats => (_new)
      }

  println("part one: " + findAttractor(startingRoom).countOccupiedSeats)
  println("part two: " + findAttractor(startingRoom, newRules = true).countOccupiedSeats)
}
