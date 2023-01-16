package chapter3

import chapter3.ListUtil.head
import chapter3.ListUtil.tail

object QuickSort {
  fun recursive(list: List<Int>): List<Int> = when {
    list.isEmpty() -> emptyList()
    1 == list.size -> list
    else -> {
      val pivot = list.head()
      val divided = list.tail().partition { it < pivot }
      recursive(divided.first) + pivot + recursive(divided.second)
    }
  }
}