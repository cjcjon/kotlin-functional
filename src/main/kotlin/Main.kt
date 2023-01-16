import chapter3.Binary
import chapter3.Maximum
import chapter3.Replicate
import chapter3.Reverse

fun main() {
  println(Maximum.recursion(listOf(1, 3, 2, 8, 4)))
  println(Reverse.recursion("abcd"))
  println(Binary.recursion(5))
  println(Replicate.recursion(3, 5))
}