package chapter7

fun <T> identity(x: T): T = x

fun main() {
  // Maybe 1 laws 성립
  println(Nothing.fmap { identity(it) } == Nothing)
  println(Just(5).fmap { identity(it) } == Just(5))

  // Tree 1 laws 성립
  val tree = Node(1, Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree))
  println(EmptyTree.fmap { identity(it) } == EmptyTree)
  println(tree.fmap { identity(it) } == tree)

  // Either1 laws 성립
  println(Left("error").fmap { identity(it) } == Left("error"))
  println(Right(5).fmap { identity(it) } == Right(5))
}