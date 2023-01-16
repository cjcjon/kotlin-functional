package chapter3

object Reverse {
  fun String.head() = first()
  fun String.tail() = drop(1)

  fun recursion(str: String): String = when {
    str.isEmpty() -> ""
    else -> recursion(str.tail()) + str.head()
  }
}