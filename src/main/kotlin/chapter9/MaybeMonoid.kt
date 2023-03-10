package chapter9

import chapter7.Just
import chapter7.Maybe
import chapter7.Nothing

object MaybeMonoid {

  fun <T> monoid(inValue: Monoid<T>) = object : Monoid<Maybe<T>> {

    override fun mempty(): Maybe<T> = Nothing

    override fun mappend(m1: Maybe<T>, m2: Maybe<T>): Maybe<T> = when {
      m1 is Nothing -> m2
      m2 is Nothing -> m1
      m1 is Just && m2 is Just -> Just(inValue.mappend(m1.value, m2.value))
      else -> Nothing
    }
  }
}

fun main() {
  val x = Just(1)
  val y = Just(2)
  val z = Just(3)

  MaybeMonoid.monoid(ProductMonoid()).run {
    println(mappend(mempty(), x) == x)                              // "true"
    println(mappend(x, mempty()) == x)                              // "true"
    println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z))) // "true"
  }

  MaybeMonoid.monoid(SumMonoid()).run {
    println(mappend(mempty(), x) == x)                              // "true"
    println(mappend(x, mempty()) == x)                              // "true"
    println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z))) // "true"
  }
}
