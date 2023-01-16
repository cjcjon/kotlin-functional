package chapter3

object Factorial {
  fun recursion(n: Int): Int = when (n) {
    1 -> 1
    else -> n * recursion(n - 1)
  }
}