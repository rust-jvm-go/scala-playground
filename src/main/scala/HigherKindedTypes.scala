/**
 * https://www.baeldung.com/scala/higher-kinded-types
 */
object HigherKindedTypes extends App {

  trait Collection[T[_]] {
    def wrap[A](a: A): T[A]
    def first[B](b: T[B]): B
  }

  var listCollection = new Collection[List] {
    override def wrap[A](a: A): List[A] = List(a)
    override def first[B](b: List[B]): B = b.head
  }
  assert(listCollection.wrap("Some values") == List("Some values"))
  assert(listCollection.first(List("Some values")) == "Some values")

  var seqCollection = new Collection[Seq] {
    override def wrap[A](a: A): Seq[A] = Seq(a)
    override def first[B](b: Seq[B]): B = b.head
  }
  assert(seqCollection.wrap("Some values") == Seq("Some values"))
  assert(seqCollection.first(Seq("Some values")) == "Some values")

  trait BatchRun[M[_]] {
    def write[A](item: A, db: M[A]): M[A] = transform(item, db)
    def transform[A](item: A, db: M[A]): M[A]
  }

  val listDb: List[String] = List("data 1", "data 2")
  var listBatchRun = new BatchRun[List] {
    def transform[A](item: A, db: List[A]): List[A] = db ::: item :: Nil
  }
  val savedList = listBatchRun.write("data 3", listDb)
  assert(savedList == List("data 1", "data 2", "data 3"))

  val seqDb: Seq[Int] = Seq(1, 2)
  val seqBatchRun = new BatchRun[Seq] {
    def transform[A](item: A, db: Seq[A]): Seq[A] = db :+ item
  }
  val savedSeq = seqBatchRun.write(3, seqDb)
  assert(savedSeq == Seq(1, 2, 3))
}
