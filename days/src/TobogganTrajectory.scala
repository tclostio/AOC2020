import scala.io.Source

case class Grid(w: Int, h: Int, content: Array[Boolean]) {
  def containsTree(x: Int, y: Int) = content(y * w + x)
  def position(x: Int, y: Int) = 
    if(y >= h) None
    else Some(GridPosition(x % w, y, this))

  def countTrees(run: Int, fall: Int) = {
    LazyList.iterate(position(0, 0))(_.flatMap(_.move(run, fall)))
      .takeWhile(_.isDefined)
      .count(_.exists(_.containsTree))
  }
}

case class GridPosition(x: Int, y: Int, grid: Grid) {
  def containsTree = grid.containsTree(x, y)
  def move(right: Int, down: Int) = grid.position(x + right, y + down)
}

object TobogganTrajectory extends App {
  val inputLines = Source.fromResource("day3.txt").getLines.toSeq

  val input = 
    inputLines
      .flatMap(_.map(spot => spot == '#'))
      .toArray

  val grid = Grid(inputLines.head.length, inputLines.length, input)

  println(grid.countTrees(3, 1))

  println(
    grid.countTrees(1, 1).toLong *
    grid.countTrees(3, 1) *
    grid.countTrees(5, 1) *
    grid.countTrees(7, 1) *
    grid.countTrees(1, 2)
  )
}
