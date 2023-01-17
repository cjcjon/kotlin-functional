package chapter3

object EvenOdd {
  fun odd(n: Int): Bounce<Boolean> = when (n) {
    0 -> Done(false)
    else -> More { even(n - 1) }
  }

  fun even(n: Int): Bounce<Boolean> = when (n) {
    0 -> Done(true)
    else -> More { odd(n - 1) }
  }
}