import chapter3.*

fun main() {
  println(trampoline(EvenOdd.even(999999)))
  println(trampoline(EvenOdd.odd(999999)))
  println(Factorial.trampoline(10))
}