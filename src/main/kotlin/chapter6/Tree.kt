package chapter6

sealed class Tree<out T>
object EmptyTree : Tree<Nothing>()
data class Node<out T>(val value: T, val left: Tree<T>, val right: Tree<T>) : Tree<T>()

fun Tree<Int>.insert(elem: Int): Tree<Int> = when (this) {
  is EmptyTree -> Node(value = elem, left = EmptyTree, right = EmptyTree)
  is Node ->
    if (elem <= value) Node(value = value, left = left.insert(elem), right = right)
    else Node(value = value, left = left, right = right.insert(elem))
}
