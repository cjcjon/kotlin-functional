package chapter3

object Binary {
  fun recursion(n: Int): String = when (n) {
    0 -> "0"
    1 -> "1"
    else -> "${recursion(n / 2)}${n % 2}"
  }

  tailrec fun tailrecRecursion(n: Int, acc: String = ""): String = when (n) {
    0 -> "0$acc"
    1 -> "1$acc"
    else -> tailrecRecursion(n / 2, "${n % 2}$acc")
  }
}