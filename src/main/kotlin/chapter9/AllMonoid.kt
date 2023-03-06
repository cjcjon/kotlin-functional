package chapter9

class AllMonoid : Monoid<Boolean> {

  override fun mempty(): Boolean = true

  override fun mappend(m1: Boolean, m2: Boolean): Boolean = m1 && m2
}

fun main() {
  val x = true
  val y = false
  val z = true

  AllMonoid().run {
    println(mappend(mempty(), x) == x)                              // "true"
    println(mappend(x, mempty()) == x)                              // "true"
    println(mappend(mappend(x, y), z) == mappend(x, mappend(y, z))) // "true"
  }
}
