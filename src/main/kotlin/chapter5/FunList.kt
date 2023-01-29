package chapter5

sealed class FunList<out T> {
  object Nil : FunList<Nothing>()

  data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun <T> FunList<T>.mkString(separator: String = ","): String {
  tailrec fun innerAcc(cons: FunList<T>, acc: String): String = when (cons) {
    is FunList.Nil -> acc
    is FunList.Cons -> innerAcc(
      cons.tail,
      if (acc.isEmpty()) cons.head.toString() else "${acc}${separator}${cons.head}"
    )
  }

  return innerAcc(this, "")
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
  is FunList.Nil -> acc
  is FunList.Cons -> tail.reverse(acc.addHead(head))
}

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
  is FunList.Nil -> FunList.Cons(value, acc).reverse()
  is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
  is FunList.Nil -> throw NoSuchElementException()
  is FunList.Cons -> tail
}

fun <T> FunList<T>.getHead(): T = when (this) {
  is FunList.Nil -> throw NoSuchElementException()
  is FunList.Cons -> head
}

tailrec fun <T> FunList<T>.filter(acc: FunList<T> = FunList.Nil, filterFunc: (T) -> Boolean): FunList<T> = when (this) {
  is FunList.Nil -> acc.reverse()
  is FunList.Cons ->
    if (filterFunc(head)) tail.filter(acc.addHead(head), filterFunc)
    else tail.filter(acc, filterFunc)
}

tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when {
  n < 1 -> this
  this is FunList.Cons -> tail.drop(n - 1)
  else -> FunList.Nil
}

tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when {
  this is FunList.Cons && !p(head) -> tail.dropWhile(p)
  else -> this
}

tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = FunList.Nil): FunList<T> = when {
  n > 0 && this is FunList.Cons -> tail.take(n - 1, acc.addHead(head))
  else -> acc.reverse()
}

tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> = when {
  this is FunList.Cons && p(head) -> tail.takeWhile(acc.addHead(head), p)
  else -> acc.reverse()
}

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when (this) {
  is FunList.Nil -> acc.reverse()
  is FunList.Cons -> tail.map(acc.addHead(f(head)), f)
}

tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = FunList.Nil, f: (Int, T) -> R): FunList<R> =
  when (this) {
    is FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.indexedMap(index + 1, acc.addHead(f(index, head)), f)
  }

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
  is FunList.Nil -> acc
  is FunList.Cons -> tail.foldLeft(f(acc, head), f)
}

fun FunList<Int>.sum(): Int = foldLeft(0) { acc, x -> acc + x }

fun toUpper(list: FunList<Char>): FunList<Char> = list.foldLeft(FunList.Nil) { acc: FunList<Char>, char: Char ->
  acc.appendTail(char.uppercaseChar())
}

fun <T, R> FunList<T>.mapByFoldLeft(f: (T) -> R): FunList<R> = foldLeft(FunList.Nil) { acc: FunList<R>, x ->
  acc.appendTail(f(x))
}

fun FunList<Int>.maximumByFoldLeft(): Int = foldLeft(0) { acc, x ->
  if (x > acc) x else acc
}

fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> = foldLeft(FunList.Nil) { acc: FunList<T>, x ->
  if (p(x)) acc.appendTail(x) else acc
}

fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
  is FunList.Nil -> acc
  is FunList.Cons -> f(head, tail.foldRight(acc, f))
}

fun <T> FunList<T>.reverseByFoldRight(): FunList<T> = foldRight(FunList.Nil) { x, acc: FunList<T> ->
  acc.appendTail(x)
}

fun <T> FunList<T>.filterByFoldRight(p: (T) -> Boolean) = foldRight(FunList.Nil) { x, acc: FunList<T> ->
  if (p(x)) acc.addHead(x) else acc
}

fun <T, R> FunList<T>.mapByFoldRight(f: (T) -> R): FunList<R> = foldRight(FunList.Nil) { x, acc: FunList<R> ->
  acc.addHead(f(x))
}

tailrec fun <T, R> FunList<T>.zip(other: FunList<R>, acc: FunList<Pair<T, R>> = FunList.Nil): FunList<Pair<T, R>> =
  when {
    other is FunList.Nil || this is FunList.Nil -> acc.reverse()
    else -> this.getTail().zip(other.getTail(), acc.addHead(this.getHead() to other.getHead()))
  }

tailrec fun <T1, T2, R> FunList<T1>.zipWith(
  f: (T1, T2) -> R,
  other: FunList<T2>,
  acc: FunList<R> = FunList.Nil
): FunList<R> = when {
  this === FunList.Nil || other === FunList.Nil -> acc.reverse()
  else -> getTail().zipWith(f, other.getTail(), acc.addHead(f(getHead(), other.getHead())))
}

fun <T, R> FunList<T>.associate(f: (T) -> Pair<T, R>): Map<T, R> = foldLeft(mapOf()) { acc: Map<T, R>, x ->
  acc.plus(f(x))
}

fun <T, K> FunList<T>.groupBy(f: (T) -> K): Map<K, FunList<T>> = foldLeft(mapOf()) { acc: Map<K, FunList<T>>, x ->
  acc.plus(f(x) to (acc.getOrDefault(f(x), FunList.Nil).appendTail(x)))
}

tailrec fun <T> FunList<T>.toString(acc: String): String = when (this) {
  is FunList.Nil -> "[${acc.drop(2)}]"
  is FunList.Cons -> tail.toString("${acc}, ${head.toString()}")
}

fun <T> FunList<T>.toStringByFoldLeft(): String =
  "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

fun main() {
  val intList = funListOf(1, 2, 3, 2)

  println(intList.toString("") == "[1, 2, 3, 2]")
  println(intList.toStringByFoldLeft() == "[1, 2, 3, 2]")
}