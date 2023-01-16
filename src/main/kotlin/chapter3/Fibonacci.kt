package chapter3

object Fibonacci {
  fun dynamic(n: Int, fibo: IntArray): Int {
    fibo[0] = 0
    fibo[1] = 1

    for (i in 2..n) {
      fibo[i] = fibo[i - 1] + fibo[i - 2]
    }

    return fibo[n]
  }

  // 100 넘으면 터짐
  fun recursion(n: Int): Int = when (n) {
    0 -> 0
    1 -> 1
    else -> recursion(n - 1) + recursion(n - 2)
  }


}