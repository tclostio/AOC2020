import scala.io.Source
import helpers._

object CustomCustoms extends App {
  val input = 
    LazyList.from(
      Source.fromResource("day6.txt")
        .getLines
    )
      .split("")

  val questionsWithAnyYes = 
    input
      .map(_            // get the coutnt for each group
        .map(_.toSet)   // turn each individual's answers into a set
        .reduce(_ ++ _) // union of the group's answers
        .size           // count the answers for the group
      )
      .sum              

  println(s"part one: $questionsWithAnyYes")

  val questionsWithAllYes = 
    input
      .map(_ 
        .map(_.toSet) 
        .reduce(_ & _) // intersection of the group's answers
        .size 
      )
    .sum

  println(s"part two: $questionsWithAllYes")
}
