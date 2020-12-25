import scala.io.Source

object LobbyLayout extends App {
  def input = 
    Source.fromResource("day24.txt")
      .getLines()
      .map(delimit)

  def delimit(identifier: String): List[String] = 
    identifier match {
      case "" => Nil
      case s"e$t"  => "e"  :: delimit(t)
      case s"se$t" => "se" :: delimit(t)
      case s"sw$t" => "sw" :: delimit(t)
      case s"w$t"  => "w"  :: delimit(t)
      case s"nw$t" => "nw" :: delimit(t)
      case s"ne$t" => "ne" :: delimit(t)
    }

  /*
  grid system working like this
      / \ /  \
    |0,1|1,-1|
  /  \ / \ /  \
  |-1,0|0,0|0, 1|
  \  / \ / \  /
    |0,1|1,1|
      \ / \ /
  in other words, NW -> N, SW => S
  */
  def coordinateDelta(direction: String) = direction match {
    case "e"  => ( 1, -1,  0)
    case "se" => ( 0, -1,  1)
    case "sw" => (-1,  0,  1)
    case "w"  => (-1,  1,  0)
    case "nw" => ( 0,  1, -1)
    case "ne" => ( 1,  0, -1)
  }

  def findTilesThatNeedFlipping(identifers: Seq[Seq[String]]) =
    identifers
      .map(_
        .map(coordinateDelta)
        .fold((0, 0, 0)) { case ((x1, y1, z1), (x2, y2, z2)) => (x1 + x2, y1 + y2, z1 + z2) }
      )
      .foldLeft(Set.empty[(Int, Int, Int)]) { case (toBlack, tile) => 
        if(toBlack.contains(tile)) toBlack - tile 
        else toBlack + tile 
      }

  println("part one: " + findTilesThatNeedFlipping(input.toSeq).size)

  // part two is basically identical to day 11: Seating System but in hex coordinates
  // I don't feel the need to do that twice
}
