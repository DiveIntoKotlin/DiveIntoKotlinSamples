interface Applicative<F> : Functor<F> {
	fun <A> pure(a: A): Kind<F, A>
	fun <A, B> Kind<F, A>.ap(f: Kind<F, (A) -> B>): Kind<F, B>
	override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> {
		return ap(pure(f))
	}
}