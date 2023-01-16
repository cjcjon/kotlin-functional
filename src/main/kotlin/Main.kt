import chapter3.Gcd
import chapter3.QuickSort
import chapter3.Zip
import kotlin.random.Random

fun main() {
  println(Zip.recursive(listOf(1, 3, 5), listOf(2, 4)))
  println(QuickSort.recursive(List(100) { Random.nextInt(0, 100) }))
  println(Gcd.recursive(75, 30))
}