package chapter9

sealed class FunList<out T> : Foldable<T> {
  override fun <B> foldLeft(acc: B, f: (B, T) -> B): B = when (this) {
    is Nil -> acc
    is Cons -> tail.foldLeft(f(acc, head), f)
  }
}

object Nil : FunList<Nothing>()

data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()

fun <A> FunList<A>.contains(value: A) = foldMap({ it == value }, AnyMonoid())

fun main() {

  val list1 = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

  require(list1.foldLeft(0) { acc, value -> acc + value } == 10)

  require(list1.foldMap({ x -> x + 1 }, SumMonoid()) == 14)
  require(list1.foldMap({ x -> x * 2 }, SumMonoid()) == 20)
}