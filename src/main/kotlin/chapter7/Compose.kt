package chapter7

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
  return { gInput: G -> this(g(gInput)) }
}

fun main() {
  val f = { a: Int -> a + 1 }
  val g = { b: Int -> b * 2 }

  /* Maybe 펑터2법칙 성립 */
  val nothingLeft = Nothing.fmap(f compose g)
  // 컴파일오류: val nothingRight = Nothing.fmap(f) compose Nothing.fmap(g)
  val nothingRight = Nothing.fmap(g).fmap(f)
  println(nothingLeft == nothingRight) // true

  val justLeft = Just(5).fmap(f compose g)
  // 컴파일오류: val justRight = Just(5).fmap(f) compose Just(0).fmap(g)
  val justRight = Just(5).fmap(g).fmap(f)
  println(justLeft == justRight) // true

  /* Tree 펑터2법칙 성립 */
  val tree = Node(1, Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree))
  println(EmptyTree.fmap(f compose g) == EmptyTree.fmap(g).fmap(f)) // true
  println(tree.fmap(f compose g) == tree.fmap(g).fmap(f)) // true

  /* Either 펑터2법칙 성립 */
  println(Left("error").fmap(f compose g) == Left("error").fmap(g).fmap(f)) // true
  println(Right(5).fmap(f compose g) == Right(5).fmap(g).fmap(f)) // true

  /* FunList 펑터2법칙 성립 */
  val funList = FunList.Cons(5, FunList.Cons(6, FunList.Nil))
  println(FunList.Nil.fmap(f compose g) == FunList.Nil.fmap(g).fmap(f)) // true
  println(funList.fmap(f compose g) == funList.fmap(g).fmap(f)) // true
}