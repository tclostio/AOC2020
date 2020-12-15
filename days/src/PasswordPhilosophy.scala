import scala.io.Source

object PasswordPhilosophy extends App {
  val inputPattern = raw"(\d+)-(\d+) (\w): (\w+)".r

  val input = Source
    .fromResource("day2.txt")
    .getLines()
    .map { 
      case inputPattern(lo, hi, char, pw) => (lo.toInt, hi.toInt, char.head, pw) 
    }
    .toSeq

  def passwordValid(lo: Int, hi: Int, char: Char, pw: String) = {
    val count = pw.count(_ == char)
    lo <= count && count <= hi
  }

  def countValid(
    passwords: Seq[(Int, Int, Char, String)], 
    validator: (Int, Int, Char, String) => Boolean
  ) = passwords.count(validator.tupled)

  println(countValid(input, passwordValid))

  def passwordValid2(lo: Int, hi: Int, char: Char, pw: String) = 
    pw.charAt(lo -1) == char ^ pw.charAt(hi -1) == char
  
  println(countValid(input, passwordValid2))
}
