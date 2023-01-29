package chapter5

tailrec fun IntProgression.toFunList(acc: FunList<Int> = FunList.Nil): FunList<Int> = when {
  step > 0 -> when {
    first > last -> acc.reverse()
    else -> ((first + step)..last step step).toFunList(acc.addHead(first))
  }
  else -> when {
    first >= last -> {
      IntProgression.fromClosedRange(first + step, last, step).toFunList(acc.addHead(first))
    }
    else -> {
      acc.reverse()
    }
  }
}

fun IntProgression.toFunStream(): FunStream<Int> = when {
  step > 0 -> when {
    first > last -> FunStream.Nil
    else -> FunStream.Cons({ first }, { ((first + step)..last step step).toFunStream() })
  }
  else -> when {
    first >= last -> {
      FunStream.Cons({ first },
        { IntProgression.fromClosedRange(first + step, last, step).toFunStream() })
    }
    else -> {
      FunStream.Nil
    }
  }
}

fun main() {
  val bigIntList = (1..10000000).toFunList()
  val listStart = System.currentTimeMillis()
  funListWay(bigIntList)
  println("${System.currentTimeMillis() - listStart} ms")

  val bigIntStream = (1..10000000).toFunStream()
  val streamStart = System.currentTimeMillis()
  funStreamWay(bigIntStream)
  println("${System.currentTimeMillis() - streamStart} ms")
}

fun funListWay(intList: FunList<Int>): Int = intList
  .map { n -> n * n }
  .filter { n -> n < 1000000 }
  .map { n -> n - 2 }
  .filter { n -> n < 1000 }
  .map { n -> n * 10 }
  .getHead()

fun funStreamWay(intList: FunStream<Int>): Int = intList
  .map { n -> n * n }
  .filter { n -> n < 1000000 }
  .map { n -> n - 2 }
  .filter { n -> n < 1000 }
  .map { n -> n * 10 }
  .getHead()