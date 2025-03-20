import cats.*
import cats.instances.int.*
import cats.syntax.show.*
import cats.syntax.all.*

import java.time.*
import java.time.format.DateTimeFormatter

object Cats1 extends App {

  val str1 = Show.apply[Int].show(21)

  val str2 = 22.show

  println(s"str1 = $str1")
  println(s"str2 = $str2")

  // -------------------------------------------------------------------------------------------------------------------

  // Without Eq

  val userOption = Option("user")
  val optionCompare = userOption.fold(false)(_ == "user")
  println(s"optionCompare = $optionCompare")

  val userEither = Right("user")
  val eitherCompare = userEither.fold(_ => false, _ == "user")
  println(s"eitherCompare = $eitherCompare")

  val filtered = List(Option(1), Option(2), Option(3), None).filter(_.fold(true)(_ != 2))
  println(s"filtered = $filtered")

  // With Eq

  val userOptionCats = Option("userCats")
  val optionCompareCats = userOptionCats === "userCats".some // true
  println(s"optionCompareCats = $optionCompareCats")

  val userEitherCats = Right("user")
  val eitherCompareCats = userEitherCats === "userCats".asRight // false
  println(s"eitherCompareCats = $eitherCompareCats")

  val filteredCats = List(Option(1), Option(2), Option(3), None).filter(_ =!= 2.some)
  println(s"filteredCats = $filteredCats")

  // -------------------------------------------------------------------------------------------------------------------

  // Show

  implicit val dateShow: Show[LocalDate] = Show.show(date => date.format(DateTimeFormatter.ISO_LOCAL_DATE))

  println(s"LocalDate.now() = ${LocalDate.now().show}")

  // Monoids

  1 |+| 2 // 3

  Option(3) |+| Option(4) |+| None |+| Option(1) // Some(8)

  def addAll[A](values: List[A])(implicit monoid: Monoid[A]): A = values.foldRight(monoid.empty)(_ |+| _)

  val aa1 = addAll(List(1, 2, 3)) // 6

  val aa2 = addAll(List(Option(1), Option(2), None, Option(3))) // Some(6)

  println(s"aa1 = $aa1")
  println(s"aa2 = $aa2")

  // Functors: 1) Chaining functions

  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 = (a: Int) => a.toString + "!"
  val func4 = func1.map(func2).map(func3)

  println(s"func4(123) = ${func4(123)}")
  // res1: String = 248!

  // Functors: 2) Writing generic code which accepts anything which provides "map" function

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] = start.map(n => n + 1 * 2)

  println(s"doMath(Option(20)) = ${doMath(Option(20))}")
  // res3: Option[Int] = Some(22)

  println(s"doMath(List(1, 2, 3)) = ${doMath(List(1, 2, 3))}")
  // res4: List[Int] = List(3, 4, 5)
}
