package chapter3

import chapter3.ListUtil.head
import chapter3.ListUtil.tail

object Maximum {
  fun recursion(items: List<Int>): Int = when {
    items.isEmpty() -> error("empty list")
    1 == items.size -> items.head()
    else -> {
      val head = items.head()
      val tail = items.tail()
      val maxVal = recursion(tail)
      if (head > maxVal) head else maxVal
    }
  }
}