import chapter3.Binary
import chapter3.Maximum
import chapter3.Replicate
import chapter3.Reverse

fun main() {
  println(Maximum.tailrecMaximum(listOf(1, 2, 10, 5, 7, 3)))
  println(Reverse.tailrecRecursion("abcde"))
  println(Binary.tailrecRecursion(3))
  println(Replicate.tailrecRecursion(3, 5))
}