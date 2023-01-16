package chapter3

object Power {
  fun recursion(x: Double, n: Int): Double = when (n) {
    0 -> .0
    1 -> x
    else -> x * recursion(x, n - 1)
  }
}