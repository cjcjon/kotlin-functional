package chapter7

import kotlin.Nothing

sealed class FunList<out T>: Functor<T> {
  abstract override fun toString(): String
  abstract override fun <B> fmap(f: (T) -> B): FunList<B>

  object Nil : FunList<Nothing>() {

    override fun toString(): String = "Nil"

    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil
  }

  data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>() {

    override fun toString(): String = "Cons(${head}, ${tail})"

    override fun <B> fmap(f: (T) -> B): FunList<B> = Cons(f(head), tail.fmap(f))
  }
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
  is FunList.Nil -> acc
  is FunList.Cons -> tail.reverse(acc.addHead(head))
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

fun <T> FunList<T>.first(): FunList<T> = when (this) {
  is FunList.Nil -> FunList.Nil
  is FunList.Cons -> FunList.Cons(head, FunList.Nil)
}

fun <T> FunList<T>.size(): Int = when (this) {
  is FunList.Nil -> 0
  is FunList.Cons -> 1 + tail.size()
}

fun main() {
  val list = FunList.Cons(1, FunList.Cons(2, FunList.Nil))

  println(list.size()) // 2
  println(list.first()) // Cons(1, Nil)
  println(list.fmap { num -> num * 2 }) // Cons(2, Cons(4, Nil))
}