package chapter3

object PowerSet {
  fun <T> Set<T>.head() = first()
  fun <T> Set<T>.tail() = drop(1).toSet()

  fun <T> recursive(s: Set<T>): Set<Set<T>> = when {
    s.isEmpty() -> setOf(emptySet())
    else -> {
      val head = s.head()
      val restSet = recursive(s.tail())
      restSet + restSet.map { setOf(head) + it }.toSet()
    }
  }

  tailrec fun <T> tailrecRecursive(s: Set<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    s.isEmpty() -> acc
    else -> tailrecRecursive(s.tail(), acc + acc.map { it + s.head() })
  }
}

fun <T> Collection<T>.head() = first()
fun <T> Collection<T>.tail() = drop(1)

fun <T> Collection<T>.powerset() : Set<Set<T>> = powerset(this, setOf(setOf()))

tailrec fun <T> powerset(s: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
  s.isEmpty() -> acc
  else -> powerset(s.tail(), acc + acc.map { it + s.head() })
}