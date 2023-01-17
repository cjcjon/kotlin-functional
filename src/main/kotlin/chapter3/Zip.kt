package chapter3

import chapter3.ListUtil.head
import chapter3.ListUtil.tail

object Zip {
  fun recursive(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> listOf()
    else -> listOf(Pair(list1.head(), list2.head())) + recursive(list1.tail(), list2.tail())
  }

  tailrec fun tailrecRecursion(
    list1: List<Int>,
    list2: List<Int>,
    acc: List<Pair<Int, Int>> = emptyList()
  ): List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> acc
    else -> {
      val zipList = acc + listOf(Pair(list1.head(), list2.head()))
      tailrecRecursion(list1.tail(), list2.tail(), zipList)
    }
  }
}