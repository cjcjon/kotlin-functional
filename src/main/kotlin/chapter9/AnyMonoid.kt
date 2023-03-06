package chapter9

class AnyMonoid : Monoid<Boolean> {

  override fun mempty(): Boolean = false

  override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 || m2
}

fun main() {
  val x = true
  val y = false
  val z = true

  AnyMonoid().run {
    println(mappend(mempty(), x) == x)                              // "true"
    println(mappend(x, mempty()) == x)                              // "true"
    println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z))) // "true"
  }
}
