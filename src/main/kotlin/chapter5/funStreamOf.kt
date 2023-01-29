package chapter5

fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

private fun <T> Array<out T>.toFunStream(): FunStream<T> = when {
  this.isEmpty() -> FunStream.Nil
  else -> FunStream.Cons({ this[0] }, { this.copyOfRange(1, this.size).toFunStream() })
}