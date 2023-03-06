package chapter9

sealed class BinaryTree<out A> : Foldable<A> {

  override fun <B> foldLeft(acc: B, f: (B, A) -> B): B = when (this) {
    is EmptyTree -> acc
    is Node -> {
      val leftAcc = leftTree.foldLeft(acc, f)
      val rootAcc = f(leftAcc, value)
      rightTree.foldLeft(rootAcc, f)
    }
  }
}

data class Node<A>(
  val value: A,
  val leftTree: BinaryTree<A> = EmptyTree,
  val rightTree: BinaryTree<A> = EmptyTree
) : BinaryTree<A>()

object EmptyTree : BinaryTree<kotlin.Nothing>()

fun <A> BinaryTree<A>.contains(value: A) = foldMap({ it == value }, AnyMonoid())

fun main() {
  val tree = Node(1, Node(2, Node(3), Node(4)), Node(5, Node(6), Node(7)))

  println(tree.foldLeft(0) { a, b -> a + b }) // "28"
  println(tree.foldLeft(1) { a, b -> a * b }) // "5040"

  println(tree.foldMap({ a -> a * 2 }, SumMonoid()))      // "56"
  println(tree.foldMap({ a -> a + 1 }, ProductMonoid()))  // "40320"

  val tree2 = Node("a", Node("b", Node("c"), Node("d")), Node("e", Node("f"), Node("g")))

  println(tree2.contains("c")) // "True"
  println(tree2.contains("z")) // "False"
}