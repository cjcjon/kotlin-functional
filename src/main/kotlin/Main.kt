import chapter3.Factorial
import chapter3.Fibonacci
import chapter3.Power

fun main() {
  println(Fibonacci.dynamic(10, IntArray(100)))
  println(Fibonacci.recursion(10))
  println(Power.recursion(3.0, 3))
  println(Factorial.recursion(5))
}