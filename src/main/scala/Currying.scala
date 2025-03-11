import cats.effect.{IO, IOApp, ExitCode}

object Currying extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    println("Running Currying...")

    val multiplication: (Int, Int) => Int = (x, y) => x * y
    val curriedMultiplication: Int => Int => Int = x => y => x * y

    val multiplicationResult = multiplication(3, 5)
    val curriedMultiplicationResult = curriedMultiplication(3)(5)
    assert(multiplicationResult == curriedMultiplicationResult)
    println(s"multiplication result: $multiplicationResult, curried result: $curriedMultiplicationResult")

    // Partial application: Because the function is curried, you can partially apply it. For example:
    val multiplyByFive: Int => Int = curriedMultiplication(5)
    val result: Int = multiplyByFive(10) // result is 50, since 5 * 10 = 50
    println(s"multiplyByFive result: $result")

    IO(println("Bye for now!")).as(ExitCode.Success)
}
