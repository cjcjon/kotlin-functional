package chapter3

import chapter3.ListUtil.head
import chapter3.ListUtil.tail

object Elem {
  fun recursion(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> (num == list.head()) || recursion(num, list.tail())
  }

  tailrec fun tailrecRecursion(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    list.head() == num -> true
    else -> tailrecRecursion(num, list.tail())
  }
}