import scala.annotation.tailrec

object RambunctiousRecitation extends App {
  
  @tailrec
  def iterateHistory(n: Int, history: Seq[(Int, Int)]): Seq[(Int, Int)] = 
    if (history.size == n) history 
    else 
    {
      val (lastSpokenN, lastTurnN) = history.head
      val maybePreviousTurnN = history.tail.collectFirst { 
        case (number, turn) if number == lastSpokenN => turn 
      }

      val numberToSpeak = maybePreviousTurnN.map(lastTurnN - _).getOrElse(0)

      iterateHistory(n, (numberToSpeak, lastTurnN +1) +: history)
    }

  def generateHistory(n: Int, startingNumbers: Seq[Int]) =
    iterateHistory(n, startingNumbers.zip(1 to startingNumbers.length).reverse)

  println("part one: " + generateHistory(2020, Seq(1,2,16,19,18,0)).head._1)
}
