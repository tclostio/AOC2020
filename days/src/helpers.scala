import scala.annotation.tailrec
object helpers {
  implicit class SeqSyntax[A](val list: Seq[A]) extends AnyVal {
    @tailrec
    def split(
        on: A, 
        pastGroups: Seq[Seq[A]] = LazyList.empty, 
        currentGroup: Seq[A] = Vector.empty
      ): Seq[Seq[A]] = list match {
        case head +: tail if head == on => 
          tail.split(on, pastGroups :+ currentGroup, Vector.empty)
          
        case head +: tail => tail.split(on, pastGroups, currentGroup :+ head)

        case Seq() => pastGroups :+ currentGroup
      }
  }

  implicit class IteratorSyntax[A](val it: Iterator[A]) extends AnyVal {
    def split(on: A => Boolean): Iterator[Iterator[A]] = 
      if(it.isEmpty) Iterator.empty
      else {
        val (head, tail) = it.span(on)
        Iterator.single(head) ++ tail.split(on)
      }
  }
}
