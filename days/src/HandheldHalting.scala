import scala.io.Source
import scala.annotation.tailrec
import scala.collection.immutable.Vector
object HandheldHalting extends App {
  val instruction = raw"(\w+) ([+-]\d+)".r

  val input = 
    Source.fromResource("day8.txt")
      .getLines
      .map { case instruction(operation, param) => (operation, param.toInt) }
      .toVector
      
  @tailrec
  def runUntilLoop(
    program: Seq[(String, Int)], 
    address: Int      = 0, 
    acc:     Long     = 0,
    path:    Seq[Int] = Nil
  ): Long = 
    if (path.contains(address)) acc
    else program(address) match {
      case ("acc", δ) => 
        runUntilLoop(program, address + 1,        acc + δ, path :+ address)
      case ("jmp", distance) => 
        runUntilLoop(program, address + distance, acc,     path :+ address)
      case _ => 
        runUntilLoop(program, address + 1,        acc,     path :+ address)
    }

  println("part one: " + runUntilLoop(input))

  def runWithLoopCorrection(
    program: Seq[(String, Int)], 
    address: Int     = 0, 
    acc: Long        = 0,
    path: Seq[Int]   = Nil,
    changed: Boolean = false
  ): Option[Long] = 
    if      (address == program.length) Some(acc)
    else if (path.contains(address))    None
    else program(address) match {

      case ("acc", δ) => 
        runWithLoopCorrection(
          program, 
          address + 1, 
          acc + δ, 
          path :+ address, 
          changed
        )

      case ("jmp", distance) => 
        runWithLoopCorrection(
          program, 
          address + distance, 
          acc, 
          path :+ address, 
          changed
        )
          .orElse(
              if (changed) None
              else 
                runWithLoopCorrection(
                  program.updated(address, ("nop", 0)), 
                  address, 
                  acc,
                  path, 
                  changed = true
                )
            )

      case ("nop", param) => 
        runWithLoopCorrection(
          program, 
          address + 1, 
          acc, 
          path :+ address, 
          changed
        )
          .orElse(
            if (changed) None
            else 
              runWithLoopCorrection(
                program.updated(address, ("jmp", param)), 
                address, 
                acc,
                path,
                changed = true
              )
          )
    }

  println("part two: " + runWithLoopCorrection(input))
}
