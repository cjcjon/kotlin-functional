package chapter7

data class UnaryFunction<in T, out R>(val g: (T) -> R): Functor<R> {
  override fun <R2> fmap(f: (R) -> R2): UnaryFunction<T, R2> {
    return UnaryFunction { x: T -> f(g(x)) }
  }

  /*
  * override fun <R2> fmap(f: (R) -> R2) = UnaryFunction { x: T -> (f compose g)(x) }
  * */

  fun invoke(input: T): R = g(input)
}

fun main() {
  val f = { a: Int -> a + 1 }
  val g = { b: Int -> b * 2 }
  val k = { b: Int -> Just(b) }

  // 함수 체이닝
  val fg = UnaryFunction(g).fmap(f)
  println(fg.invoke(5)) // 11

  // 컨텍스트 변경 (Maybe) (lifting 함수라 한다)
  val kg = UnaryFunction(g).fmap(k)
  println(kg.invoke(5)) // Just(10)
}
