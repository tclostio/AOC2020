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

    def firstOpt[B](f: A => Option[B]) = list.collectFirst(Function.unlift(f))

    def collectFirst2[B](f: PartialFunction[(A, A), B]) = {      
      @tailrec
      def recurse(
        entries1: Seq[A],
        entries2: Seq[A]
      ): Option[B] =  
        (entries1, entries2) match {
          case (e1 +: _, e2 +: _) if f.isDefinedAt(e1, e2) => f.lift(e1, e2)
          case (es1, _ +: es2) => recurse(es1, es2)
          case (_ +: es1, Nil) => recurse(es1, list)
          case _ => None
        }

      recurse(list, list)
    }

    def collectFirst3[B](f: PartialFunction[(A, A, A), B]) = 
      list.firstOpt(a =>
      list.firstOpt(b => 
      list.firstOpt(c => f.lift(a, b, c)
    )))

    def foldWhile[B](acc: B)(f: PartialFunction[(B, A), B]) = 
      helpers.foldWhile(list)(acc)(f)
  }

  implicit class IteratorSyntax[A](val it: Iterator[A]) extends AnyVal {
    def split(on: A => Boolean): Iterator[Iterator[A]] = 
      if(it.isEmpty) Iterator.empty
      else {
        val (head, tail) = it.span(on)
        Iterator.single(head) ++ tail.split(on)
      }

    def foldWhile[B](acc: B)(f: PartialFunction[(B, A), B]) = 
      helpers.foldWhile(LazyList.from(it))(acc)(f)
  }

  @tailrec
  def foldWhile[A, B](seq: Seq[A])(acc: B)(f: PartialFunction[(B, A), B]): B = 
    seq match {
      case head +: tail => 
        f.lift((acc, head)) match {
          case Some(b) => foldWhile(tail)(b)(f)
          case None => acc
        }
      case Seq() => acc
    }
}
