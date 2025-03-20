object FunctionalProgramming extends App {

  class Person(name: String) {

    val stringConcatenator1: (String, String) => String = (arg1: String, arg2: String) => arg1 + arg2

    val stringConcatenator2: (String, String) => String = (arg1: String, arg2: String) => arg1 + arg2
  }

  val me = new Person("Me")

  println(me.stringConcatenator1("I love ", "Scala!"))
  println(me.stringConcatenator2("Scala ", "rocks!"))
}
