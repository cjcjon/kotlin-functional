package chapter3

object MySequence {
  // 출처: https://agilewombat.com/2016/02/06/kotlin-sequences/
  operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }

    override fun iterator() = object : Iterator<T> {
      override fun next(): T =
        if (thisIterator.hasNext()) thisIterator.next()
        else otherIterator.next()

      override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
    }
  }

  fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }

  fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when (n) {
    0 -> listOf()
    else -> sequence.take(n).toList()
  }
}