package chapter5

sealed class FunList<out T> {
  object Nil : FunList<Nothing>()

  data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun main() {
  fun <T> printCons(cons: FunList<T>) {
    tailrec fun <T> mkString(cons: FunList<T>, acc: String): String = when (cons) {
      is FunList.Nil -> acc
      is FunList.Cons -> mkString(
        cons.tail,
        if (acc.isEmpty()) cons.head.toString() else "${acc}, ${cons.head}"
      )
    }

    println("[${mkString(cons, "")}]")
  }

  val list1 = FunList.Cons(1, FunList.Cons(2, FunList.Nil))
  val list2 = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Cons(5, FunList.Nil)))))
  val list3 = FunList.Cons(1.0, FunList.Cons(2.0, FunList.Cons(3.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

  printCons(list1)
  printCons(list2)
  printCons(list3)
}