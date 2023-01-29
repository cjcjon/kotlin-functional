package chapter5

// 문제가 이상함??
// 모든 자연수의 제곱의 합이 1000을 넘는 최소 자연수를 찾아라
tailrec fun findAddSquareRootBe1000Size(number: Int = 0, acc: Double = .0): Int = when {
  acc > 1000 -> number - 1
  else -> findAddSquareRootBe1000Size(number + 1, number * number + acc)
}

fun main() {
  println(findAddSquareRootBe1000Size())
}