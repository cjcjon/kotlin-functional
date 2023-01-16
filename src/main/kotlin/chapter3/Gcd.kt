package chapter3

object Gcd {
  fun recursive(m: Int, n: Int): Int = when (n) {
    0 -> m
    else -> recursive(n, m % n)
  }
}