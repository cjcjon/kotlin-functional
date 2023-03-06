package chapter9

import chapter5.FunList
import chapter5.FunList.Cons
import chapter5.FunList.Nil
import chapter5.concat

object ListMonoid {

  fun <T> monoid(inValue: Monoid<T>) = object : Monoid<FunList<T>> {

    override fun mempty(): FunList<T> = Nil

    override fun mappend(m1: FunList<T>, m2: FunList<T>): FunList<T> = when (m1) {
      is Nil -> m2
      is Cons -> m1 concat m2
    }
  }
}