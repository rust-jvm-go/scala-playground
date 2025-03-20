
// Reference:
// https://medium.com/swlh/introduction-to-scala-cats-part-1-3487e8d86a40

trait Printable[A] {
  def print(value: A): String
}

object PrintableInstances {

  implicit def stringPrintable: Printable[String] = (value: String) => value

  implicit def intPrintable: Printable[Int] = (value: Int) => value.toString

  implicit def optionPrintable[A]: Printable[Option[A]] = {
    case Some(value) => value.toString
    case None => ""
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def print(implicit p: Printable[A]): String = p.print(value)
  }
}

object TypeClasses1 extends App {

  import PrintableInstances._
  import PrintableSyntax._

  println("MyString".print)

  println(100.print)

  println(Option("Some String").print)

  println(Option(1).print)
}
