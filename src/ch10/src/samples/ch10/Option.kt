package samples.ch10

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> Kind<Option.K, A>.unwrap(): Option<A> =
    this as Option<A>


sealed class Option<out A> : Kind<Option.K, A> {
  object K
}

data class Some<V>(val value: V) : Option<V>()
object None : Option<Nothing>()

object OptionMonad : Monad<Option.K> {
  override fun <A, B> Kind<Option.K, A>.flatMap(f: (A) -> Kind<Option.K, B>): Kind<Option.K, B> {
    val oa = this
    return when (oa) {
      is Some -> f(oa.value)
      else -> None
    }
  }

  override fun <A> pure(a: A): Kind<Option.K, A> {
    return Some(a)
  }
}
