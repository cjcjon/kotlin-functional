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

fun <T> generateFunStream(seed: T, generate: (T) -> T): FunStream<T> =
  FunStream.Cons({ seed }, { generateFunStream(generate(seed), generate) })

tailrec fun <T> FunStream<T>.forEach(f: (T) -> Unit): Unit = when (this) {
  is FunStream.Nil -> Unit
  is FunStream.Cons -> {
    f(head())
    tail().forEach(f)
  }
}

fun <T> FunStream<T>.take(n: Int): FunStream<T> = when {
  this is FunStream.Cons && n > 0 -> FunStream.Cons(head) { tail().take(n - 1) }
  else -> FunStream.Nil
}

fun main() {
  println(generateFunStream(0) { it + 5 }.take(5) == funStreamOf(0, 5, 10, 15, 20))
}