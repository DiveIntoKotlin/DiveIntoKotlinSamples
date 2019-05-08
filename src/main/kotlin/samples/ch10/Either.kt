package samples.ch10

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A, B> Kind2<Either.K, A, B>.unwrap(): Either<A, B> =
	this as Either<A, B>


sealed class Either<out A, out B> : Kind2<Either.K, A, B> {
	object K
}

data class Right<B>(val value: B) : Either<Nothing, B>()
data class Left<A>(val value: A) : Either<A, Nothing>()


class EitherMonad<C> : Monad<Kind<Either.K, C>> {
	override fun <A, B> Kind<Kind<Either.K, C>, A>.flatMap(f: (A) -> Kind<Kind<Either.K, C>, B>): Kind<Kind<Either.K, C>, B> {
		val eab = this
		return when (eab) {
			is Right -> f(eab.value)
			is Left -> eab
			else -> TODO()
		}
	}

	override fun <A> pure(a: A): Kind<Kind<Either.K, C>, A> {
		return Right(a)
	}
}
