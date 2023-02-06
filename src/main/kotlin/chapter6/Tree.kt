package chapter6

sealed class Tree<out T>
object EmptyTree : Tree<Nothing>()
data class Node<out T>(val value: T, val left: Tree<T>, val right: Tree<T>) : Tree<T>()

