import chapter3.Elem
import chapter3.Take

fun main() {
  println(Take.recursion(3, listOf(1, 2, 3, 4, 5)))
  println(Elem.recursion(3, listOf(1, 2, 3, 4)))
  println(Elem.recursion(0, listOf(1, 2)))
}