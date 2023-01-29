package chapter5

fun <T> funListOf(vararg elements: T): FunList<T> = elements.toFunList()

private fun <T> Array<out T>.toFunList(): FunList<T> = when {
  this.isEmpty() -> FunList.Nil
  else -> FunList.Cons(this[0], this.copyOfRange(1, this.size).toFunList())
}