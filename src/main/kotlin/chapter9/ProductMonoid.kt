package chapter9

class ProductMonoid : Monoid<Int> {

  override fun mempty(): Int = 1

  override fun mappend(m1: Int, m2: Int): Int = m1 * m2
}

fun main() {
  val x = 1
  val y = 2
  val z = 3

  ProductMonoid().run {
    println(mappend(mempty(), x) == x)                              // "true"
    println(mappend(x, mempty()) == x)                              // "true"
    println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z))) // "true"
  }
}