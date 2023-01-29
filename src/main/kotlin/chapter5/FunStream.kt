package chapter5

sealed class FunStream<out T> {
  object Nil : FunStream<Nothing>()

  data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>() {
    override fun equals(other: Any?): Boolean = when {
      other is Cons<*> && head() == other.head() && tail() == other.tail() -> true
      else -> false
    }

    override fun hashCode(): Int {
      return 31 * head.hashCode() + tail.hashCode()
    }
  }
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

fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
  is FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
  is FunStream.Cons -> FunStream.Cons(head) { tail().appendTail(value) }
}

fun <T> FunStream<T>.filter(p: (T) -> Boolean): FunStream<T> = when {
  this is FunStream.Cons ->
    if (p(head())) FunStream.Cons(head) { tail().filter(p) }
    else tail().filter(p)

  else -> FunStream.Nil
}

fun <T, R> FunStream<T>.map(f: (T) -> R): FunStream<R> = when (this) {
  is FunStream.Nil -> FunStream.Nil
  is FunStream.Cons -> FunStream.Cons({ f(head()) }, { tail().map(f) })
}

fun main() {
  println(funStreamOf(1, 2, 3).map { it * 2 } == funStreamOf(2, 4, 6))
}