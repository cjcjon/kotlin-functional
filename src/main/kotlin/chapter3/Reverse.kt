package chapter3

object Reverse {
  fun String.head() = first()
  fun String.tail() = drop(1)

  fun recursion(str: String): String = when {
    str.isEmpty() -> ""
    else -> recursion(str.tail()) + str.head()
  }

  tailrec fun tailrecRecursion(str: String, acc: String = ""): String = when {
    str.isEmpty() -> acc
    else -> {
      val reversed = str.head() + acc
      tailrecRecursion(str.tail(), reversed)
    }
  }
}