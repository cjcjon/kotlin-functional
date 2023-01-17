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

  tailrec fun tailrecMaximum(items: List<Int>, acc: Int = Int.MIN_VALUE): Int = when {
    items.isEmpty() -> error("empty list")
    items.size == 1 -> {
      println("head: ${items[0]}, maxVal : $acc")
      acc
    }
    else -> {
      val head = items.head()
      val maxValue = if (head > acc) head else acc
      println("head: $head, maxVal: $maxValue")
      tailrecMaximum(items.tail(), maxValue)
    }
  }
}