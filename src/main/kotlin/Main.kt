import chapter3.*
import kotlin.random.Random

fun main() {
  println(Fibonacci.recursionMemoize(150))
  println(Factorial.recursionMemoize(5))
  println(Power.recursionMemoize(3.0, 3))
}