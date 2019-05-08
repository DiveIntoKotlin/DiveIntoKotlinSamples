package samples.ch10

interface Monad<F> : Applicative<F> {

  fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>

  override fun <A, B> Kind<F, A>.ap(f: Kind<F, (A) -> B>): Kind<F, B> {
    return f.flatMap { fn ->
      this.flatMap { a ->
        pure(fn(a))
      }
    }
  }
}
