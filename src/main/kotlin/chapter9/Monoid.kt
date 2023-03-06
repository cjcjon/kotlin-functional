package chapter9

import chapter5.FunList
import chapter5.foldRight
import chapter5.funListOf

interface Monoid<T> {

  fun mempty(): T

  fun mappend(m1: T, m2: T): T
}

fun <T> Monoid<T>.mconcat(list: FunList<T>): T = list.foldRight(mempty(), ::mappend)

fun main() {
  println(ProductMonoid().mconcat(funListOf(1, 2, 3, 4, 5)))  // "120" 출력
  println(SumMonoid().mconcat(funListOf(1, 2, 3, 4, 5)))      // "15" 출력

  // 9-5
  println(AnyMonoid().mconcat(funListOf(true, true, true)))     // true
  println(AnyMonoid().mconcat(funListOf(false, false, false)))  // false
  println(AnyMonoid().mconcat(funListOf(true, false, true)))    // true

  // 9-6
  println(AllMonoid().mconcat(funListOf(true, true, true)))     // true
  println(AllMonoid().mconcat(funListOf(false, false, false)))  // false
  println(AllMonoid().mconcat(funListOf(true, false, true)))    // true
}
