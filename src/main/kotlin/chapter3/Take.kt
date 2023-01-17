package chapter3

import chapter3.ListUtil.head
import chapter3.ListUtil.tail

object Take {
  fun recursion(n: Int, list: List<Int>): List<Int> = when {
    n <= 0 -> emptyList()
    list.isEmpty() -> emptyList()
    else -> listOf(list.head()) + recursion(n - 1, list.tail())
  }

  tailrec fun tailrecRecursion(n: Int, list: List<Int>, acc: List<Int> = emptyList()): List<Int> = when {
    n <= 0 || list.isEmpty() -> acc
    else -> {
      val takeList = acc + listOf(list.head())
      tailrecRecursion(n - 1, list.tail(), takeList)
    }
  }
}