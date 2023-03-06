package chapter9

sealed class Tree<out A> : Foldable<A>

data class TreeNode<A>(val value: A, val forest: FunList<TreeNode<A>> = Nil) : Tree<A>() {

  override fun <B> foldLeft(acc: B, f: (B, A) -> B): B = when (forest) {
    is Nil -> f(acc, value)
    is Cons -> f(loop(forest, f, acc), value)
  }

  private tailrec fun <B> loop(list: FunList<TreeNode<A>>, f: (B, A) -> B, acc: B): B = when (list) {
    is Nil -> acc
    is Cons -> loop(list.tail, f, list.head.foldLeft(acc, f))
  }
}

fun <A> Tree<A>.contains(value: A) = foldMap({ it == value }, AnyMonoid())
