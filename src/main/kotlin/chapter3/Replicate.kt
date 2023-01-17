package chapter3

object Replicate {
  fun recursion(n: Int, element: Int): List<Int> = when (n) {
    0 -> emptyList()
    else -> listOf(element) + recursion(n - 1, element)
  }

  tailrec fun tailrecRecursion(n: Int, element: Int, acc: List<Int> = emptyList()): List<Int> = when (n) {
    0 -> acc
    else -> tailrecRecursion(n - 1, element, acc + element)
  }
}