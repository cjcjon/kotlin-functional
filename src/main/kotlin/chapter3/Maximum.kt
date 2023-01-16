package chapter3

object Maximum {
  fun List<Int>.head() = first()
  fun List<Int>.tail() = drop(1)

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