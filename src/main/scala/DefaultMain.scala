import scala.util.Try

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(): Unit =
  //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
  // to see how IntelliJ IDEA suggests fixing it.
  (1 to 5).foreach(println)

  for (i <- 1 to 5) do
    //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
    // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
    println(s"i = $i")

  // Collections

  val alist = List(1, 2, 3)
  val incremented = alist.map(_ + 1) // [2, 3, 4]
  val transformed1 = alist.flatMap(i => List(i, i + 1)) // [1, 2, 2, 3, 3, 4]
  val filtered = alist.filter(_ % 2 == 0) // [2]

  println(s"incremented = $incremented")
  println(s"transformed1 = $transformed1")
  println(s"filtered = $filtered")
  println(s"alist.head = ${alist.head}, alist.last = ${alist.last}")

  val chessboard = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield s"$num-$char" // yield (num, char)

  println(chessboard)

  // "options", "try"
  // Will also have map, flatMap, filter, for-comprehension, orElse

  val anOption = Option(1)
  val transformedOption = anOption.map(_ + 1)
  val aTry: Try[Int] = Try(throw new RuntimeException("oops"))
  val transformedTry = aTry.map(_ * 10)

  println(s"transformedOption = $transformedOption")
  println(s"transformedTry = $transformedTry")

  // Monads

  // Futures

  // Contextual abstractions
