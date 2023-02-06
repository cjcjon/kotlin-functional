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

fun main() {
  val tree1 = EmptyTree.insertTailrec(5)
  require(tree1 == Node(5, EmptyTree, EmptyTree))

  val tree2 = tree1.insertTailrec(3)
  require(
    tree2 ==
        Node(
          5,
          Node(3, EmptyTree, EmptyTree),
          EmptyTree
        )
  )

  val tree3 = tree2.insertTailrec(10)
  require(
    tree3 ==
        Node(
          5,
          Node(3, EmptyTree, EmptyTree),
          Node(10, EmptyTree, EmptyTree)
        )
  )

  val tree4 = tree3.insertTailrec(20)
  require(
    tree4 ==
        Node(
          5,
          Node(3, EmptyTree, EmptyTree),
          Node(
            10,
            EmptyTree,
            Node(20, EmptyTree, EmptyTree)
          )
        )
  )

  val tree5 = tree4.insertTailrec(4)
  require(
    tree5 ==
        Node(
          5,
          Node(
            3,
            EmptyTree,
            Node(4, EmptyTree, EmptyTree)
          ),
          Node(
            10,
            EmptyTree,
            Node(20, EmptyTree, EmptyTree)
          )
        )
  )

  val tree6 = tree5.insertTailrec(2)
  require(
    tree6 ==
        Node(
          5,
          Node(
            3,
            Node(2, EmptyTree, EmptyTree),
            Node(4, EmptyTree, EmptyTree)
          ),
          Node(
            10,
            EmptyTree,
            Node(20, EmptyTree, EmptyTree)
          )
        )
  )

  val tree7 = tree6.insertTailrec(8)
  require(
    tree7 ==
        Node(
          5,
          Node(
            3,
            Node(2, EmptyTree, EmptyTree),
            Node(4, EmptyTree, EmptyTree)
          ),
          Node(
            10,
            Node(8, EmptyTree, EmptyTree),
            Node(20, EmptyTree, EmptyTree)
          )
        )
  )

  // 오래 걸리는데 빨리 돌릴거면 LinkedList로 타입을 바꾸면 된다
  (1..100000).fold(EmptyTree as Tree<Int>) { acc, i ->
    acc.insertTailrec(i)
  }
}