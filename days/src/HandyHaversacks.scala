import scala.io.Source

object HandyHaversacks extends App {
  val innerBag = raw"(\d) ([\w ]+) bag[s]?".r

  val input = 
    Source.fromResource("day7.txt")
      .getLines
      .map(_.split(" bags contain ")) // split rule into inner and outer bags
      .map { 
        case Array(outerBag, "no other bags.")  => 
          outerBag -> Map.empty[String, Int]

        case Array(outerBag, innerBagsSentence) => 
          val innerBagsList = // split inner bags into individual bag-count pairs
            innerBagsSentence
              .split(',')
              .map(_.strip.stripSuffix("."))
              .map { case innerBag(count, color) => color -> count.toInt }
          outerBag -> innerBagsList.toMap
      }
      .toMap
  
  def outerColorPossibilities(
    color: String, 
    rules: Map[String, Map[String, Int]]
  ): Set[String] = {
    val directWrappers = 
      rules
        .filter { case (_, innerColors) => innerColors.contains(color) }
        .keySet

    val indirectWrappers = 
      directWrappers.flatMap(outerColorPossibilities(_, rules))

    directWrappers ++ indirectWrappers
  }

  println("part one: " + outerColorPossibilities("shiny gold", input).size)

  def countInnerBags(
    color: String, 
    rules: Map[String, Map[String, Int]],
    outermostBag: Boolean = true
  ): Long = 
    (if (outermostBag) 0 else 1) + 
    rules(color)
      .map { case (innerColor, count) => 
        count * countInnerBags(innerColor, rules, false)
      }
      .sum 
  
  println("part two " + countInnerBags("shiny gold", input))
}
