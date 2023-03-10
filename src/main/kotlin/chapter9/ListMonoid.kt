package chapter9

import chapter5.FunList
import chapter5.concat
import chapter5.funListOf

class FunListMonoid<T> : Monoid<FunList<T>> {
  override fun mempty(): FunList<T> = FunList.Nil

  override fun mappend(m1: FunList<T>, m2: FunList<T>): FunList<T> = when (m1) {
    FunList.Nil -> m2
    is FunList.Cons -> m1 concat m2
  }
}

fun main() {
  // 9-8
  val x = funListOf(1, 2, 3)
  val y = funListOf(4, 5, 6)

  FunListMonoid<Int>().run {
    require(mappend(x, y) == funListOf(1, 2, 3, 4, 5, 6))
    require(mappend(x, FunList.Nil) == x)
    require(mappend(FunList.Nil, x) == x)
    require(mappend(y, FunList.Nil) == y)
    require(mappend(FunList.Nil, y) == y)
  }

  // 9-9
  println(FunListMonoid<Int>().mconcat(funListOf(funListOf(1, 2), funListOf(3, 4), funListOf(5))))
}