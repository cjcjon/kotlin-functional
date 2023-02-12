package chapter7

interface Functor<out A> {
  fun <B> fmap(f: (A) -> B): Functor<B>
}
