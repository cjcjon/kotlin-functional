package chapter7

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
  { p1: P1 -> { p2: P2 -> this(p1, p2) } }

fun main() {
  val product: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
  val curriedProduct: (Int) -> (Int) -> (Int) = product.curried()
  val maybeProductTen: Maybe<(Int) -> Int> = Just(10).fmap(curriedProduct)

  println(maybeProductTen.fmap { it(5) }) // Just(50)
}