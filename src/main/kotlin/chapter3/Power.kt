package chapter3

object Power {
  fun recursion(x: Double, n: Int): Double = when (n) {
    0 -> .0
    1 -> x
    else -> x * recursion(x, n - 1)
  }

  fun recursionMemoize(x: Double, n: Int): Double = run {
    tailrec fun inner(x: Double, n: Int, acc: Double): Double = when (n) {
      0 -> .0
      1 -> acc * x
      else -> inner(x, n - 1, acc * x)
    }

    inner(x, n, 1.0)
  }
}