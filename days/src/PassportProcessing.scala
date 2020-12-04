import scala.io.Source
import scala.util.Try

object PassportProcessing extends App {
  val passportField = raw"(\w+):(.+)".r

  val input = Source.fromResource("day4.txt")
    .mkString                                                // turn the whole file into a string
    .split("\\n\\n")                                         // split into passports based on empty lines
    .map(_.split(raw"\s"))                                   // split passports into field:value pairs
    .map(_.map { case passportField(k, v) => k -> v }.toMap) // turn field value pairs into a map (shocker)

  val requiredFields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  // not even worth making a function to check validity for pt 1

  println(input.count(pp => requiredFields.subsetOf(pp.keySet)))

  val hex = raw"^#\w{6}\Z".r
  val eyeColors = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

  def validatePassport(passport: Map[String, String]) = 
    passport
      .filter { // remove all fields that don't match constraints
        case ("byr", Int(yr)) => 1920 <= yr && yr <= 2002
        case ("iyr", Int(yr)) => 2010 <= yr && yr <= 2020
        case ("eyr", Int(yr)) => 2020 <= yr && yr <= 2030
        case ("hgt", s"${Int(cm)}cm") => 150 <= cm && cm <= 193
        case ("hgt", s"${Int(in)}in") => 59  <= in && in <= 76
        case ("hcl", hex())           => true
        case ("ecl", color)           => eyeColors.contains(color)
        case ("pid", id @ Int(_))     => id.length == 9
        case _ => false
      }
      .keySet == requiredFields // ensure all required fields remain
  
  println(input.count(validatePassport)) 
}

object Int {
  // try to parse the string into an integer, don't match if it fails
  def unapply(str: String) = Try(str.toInt).toOption
}
