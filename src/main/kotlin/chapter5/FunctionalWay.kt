package chapter5

fun main() {
  val bigIntList = (10000000 downTo 1).toList()

  val imperativeStart = System.currentTimeMillis()
  imperativeWay(bigIntList)
  println("${System.currentTimeMillis() - imperativeStart} ms")

  val functionalStart = System.currentTimeMillis()
  functionalWay(bigIntList)
  println("${System.currentTimeMillis() - functionalStart} ms")

  val realFunctionalStart = System.currentTimeMillis()
  realFunctionalWay(bigIntList)
  println("${System.currentTimeMillis() - realFunctionalStart} ms")
}

fun imperativeWay(intList: List<Int>): Int {
  for (value in intList) {
    val doubleValue = value * value
    if (doubleValue < 10) return doubleValue
  }

  throw NoSuchElementException("There is no value")
}

fun functionalWay(intList: List<Int>): Int =
  intList
    .map { n -> n * n }
    .filter { n -> n < 10 }
    .first()

fun realFunctionalWay(intList: List<Int>): Int =
  intList.asSequence()
    .map { n -> n * n }
    .filter { n -> n < 10 }
    .first()