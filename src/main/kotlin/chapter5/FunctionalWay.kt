package chapter5

fun main() {
  val bigIntList = (1..10000000).toList()
  val imperativeStart = System.currentTimeMillis()
  imperativeWay(bigIntList)
  println("${System.currentTimeMillis() - imperativeStart} ms")

  val functionalStart = System.currentTimeMillis()
  realFunctionalWay(bigIntList)
  println("${System.currentTimeMillis() - functionalStart} ms")
}

fun imperativeWay(intList: List<Int>): Int {
  for (value in intList) {
    val doubleValue = value * value
    if (doubleValue < 10) return doubleValue
  }

  throw NoSuchElementException("There is no value")
}

fun realFunctionalWay(intList: List<Int>): Int =
  intList.asSequence()
    .map { n -> n * n }
    .filter { n -> n < 10 }
    .first()