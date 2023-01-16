package chapter3

object Replicate {
  fun recursion(n: Int, element: Int): List<Int> = when (n) {
    0 -> emptyList()
    else -> listOf(element) + recursion(n - 1, element)
  }
}