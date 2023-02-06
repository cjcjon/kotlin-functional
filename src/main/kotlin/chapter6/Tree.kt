package chapter6

import chapter3.head
import chapter3.tail

sealed class Tree<out T>
object EmptyTree : Tree<Nothing>()
data class Node<out T>(val value: T, val left: Tree<T>, val right: Tree<T>) : Tree<T>()

fun Tree<Int>.insert(elem: Int): Tree<Int> = when (this) {
  is EmptyTree -> Node(value = elem, left = EmptyTree, right = EmptyTree)
  is Node ->
    if (elem <= value) Node(value = value, left = left.insert(elem), right = right)
    else Node(value = value, left = left, right = right.insert(elem))
}

enum class TreeDirection {
  LEFT,
  RIGHT
}

fun Tree<Int>.insertTailrec(elem: Int): Tree<Int> {

  // toInsertValue를 넣기 위한 LeaftNode가 List의 가장 앞에, Tree의 head는 List의 가장 마지막에 위치한다
  // List의 첫 값에는 현재 위치의 자기 자신(Node)이 들어가고, 두번째 값에는 어디로 이동할지 방향이 들어간다
  fun path(tree: Tree<Int>, toInsertValue: Int): List<Pair<Tree<Int>, TreeDirection>> {
    tailrec fun innerFunc(
      pathTree: Tree<Int>,
      acc: List<Pair<Tree<Int>, TreeDirection>>
    ): List<Pair<Tree<Int>, TreeDirection>> = when (pathTree) {
      is EmptyTree -> acc
      is Node ->
        if (toInsertValue < pathTree.value) innerFunc(pathTree.left, listOf(pathTree to TreeDirection.LEFT) + acc)
        else innerFunc(pathTree.right, listOf(pathTree to TreeDirection.RIGHT) + acc)
    }

    return innerFunc(tree, listOf())
  }

  // 가장 leaf node 부터 tree를 재구성
  fun rebuild(path: List<Pair<Tree<Int>, TreeDirection>>, toInsertValue: Int): Tree<Int> {
    tailrec fun innerFunc(toMovePath: List<Pair<Tree<Int>, TreeDirection>>, acc: Tree<Int>): Tree<Int> = when {
      toMovePath.isEmpty() -> acc
      else -> when (toMovePath.head().second) {
        TreeDirection.LEFT -> innerFunc(toMovePath.tail(), Node(value = (toMovePath.head().first as Node).value, left = acc, right = (toMovePath.head().first as Node).right))
        TreeDirection.RIGHT -> innerFunc(toMovePath.tail(), Node(value = (toMovePath.head().first as Node).value, left = (toMovePath.head().first as Node).left, right = acc))
      }
    }

    return innerFunc(path, Node(value = toInsertValue, left = EmptyTree, right = EmptyTree))
  }

  return rebuild(path(this, elem), elem)
}

fun Tree<Int>.contains(elem: Int): Boolean = when(this) {
  is EmptyTree -> false
  is Node ->
    if (elem == value) true
    else if (elem < value) left.contains(elem)
    else right.contains(elem)
}

fun main() {
  val tree1 = EmptyTree.insertTailrec(5).insertTailrec(3).insertTailrec(10).insertTailrec(20).insertTailrec(4)

  require(tree1.contains(5))
  require(tree1.contains(3))
  require(tree1.contains(10))
  require(tree1.contains(20))
  require(tree1.contains(4))
  require(!tree1.contains(8))
}