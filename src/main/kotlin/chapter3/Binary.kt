package chapter3

object Binary {
  fun recursion(n: Int): String = when (n) {
    0 -> "0"
    1 -> "1"
    else -> "${recursion(n / 2)}${n % 2}"
  }
}