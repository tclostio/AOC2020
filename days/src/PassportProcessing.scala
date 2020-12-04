import scala.io.Source

object PassportProcessing extends App {
  val passportField = raw"(\w+):(.+)".r

  val input = Source.fromResource("day4.txt")
    .mkString                                                // turn the whole file into a string
    .split("\\n\\n")                                         // split into passports based on empty lines
    .map(_.split(raw"\s"))                                   // split passports into field:value pairs
    .map(_.map { case passportField(k, v) => k -> v }.toMap) // turn field value pairs into a map (shocker)

  val requiredFields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  // not even worth making a function to check validity

  println(input.count(pp => requiredFields.subsetOf(pp.keySet)))
}
