package chapter3

import chapter3.ListUtil.head
import chapter3.ListUtil.tail

object Take {
  fun recursion(n: Int, list: List<Int>): List<Int> = when {
    n <= 0 -> emptyList()
    list.isEmpty() -> emptyList()
    else -> listOf(list.head()) + recursion(n - 1, list.tail())
  }
}