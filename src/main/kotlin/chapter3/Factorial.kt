package chapter3

object Factorial {
  fun recursion(n: Int): Int = when (n) {
    1 -> 1
    else -> n * recursion(n - 1)
  }

  fun recursionMemoize(n: Int) = run {
    tailrec fun inner(n: Int, acc: Int): Int = when (n) {
      0, 1 -> acc
      else -> inner(n - 1, acc * n)
    }

    inner(n, 1)
  }

  fun trampoline(n: Int) = trampoline(multiply(n, 1))

  private fun minusOne(n: Int, acc: Int): Bounce<Int> = when (n) {
    0, 1 -> Done(acc)
    else -> More { multiply(n - 1, acc) }
  }

  private fun multiply(n: Int, acc: Int): Bounce<Int> = when (n) {
    0, 1 -> Done(acc)
    else -> More { minusOne(n, acc * n) }
  }
}