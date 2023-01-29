package chapter5

sealed class FunStream<out T> {
  object Nil: FunStream<Nothing>()

  data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>): FunStream<T>()
}

fun <T> FunStream<T>.getHead(): T = when (this) {
  is FunStream.Nil -> throw NoSuchElementException()
  is FunStream.Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this) {
  is FunStream.Nil -> throw NoSuchElementException()
  is FunStream.Cons -> tail()
}

fun FunStream<Int>.sum(): Int = when (this) {
  is FunStream.Nil -> 0
  is FunStream.Cons -> head() + tail().sum()
}

fun FunStream<Int>.product(): Int = when (this) {
  is FunStream.Nil -> 1
  is FunStream.Cons -> head() * tail().product()
}

fun main() {
  println(funStreamOf(1, 2, 3, 4).product())
}