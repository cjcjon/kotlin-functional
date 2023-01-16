package chapter3

object ListUtil {
  fun List<Int>.head() = first()
  fun List<Int>.tail() = drop(1)
}