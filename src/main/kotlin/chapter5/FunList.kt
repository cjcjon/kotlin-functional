package chapter5

sealed class FunList<out T> {
  object Nil : FunList<Nothing>()

  data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun <T> FunList<T>.mkString(separator: String = ","): String {
  tailrec fun innerAcc(cons: FunList<T>, acc: String): String = when (cons) {
    is FunList.Nil -> acc
    is FunList.Cons ->  innerAcc(
      cons.tail,
      if (acc.isEmpty()) cons.head.toString() else "${acc}${separator}${cons.head}"
    )
  }

  return innerAcc(this, "")
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when(this) {
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

fun main() {
  val list = FunList.Cons(1, FunList.Cons(2, FunList.Nil))

  println("[${list.mkString()}]")
  println("head: ${list.getHead()}")
}