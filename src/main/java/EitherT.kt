data class EitherT<F, L, A>(val value: Kind<F, Either<L, A>>) {
	companion object {
		fun <F, A, B> pure(AP: Applicative<F>): (B) -> EitherT<F, A, B> = { b ->
			EitherT(AP.pure(Right(b)))
		}
	}

	fun <B> flatMap(M: Monad<F>): ((A) -> EitherT<F, L, B>) -> EitherT<F, L, B> = { f ->

		val v = M.run {
			value.flatMap { ela ->
				when (ela) {
					is Left -> M.pure(Left(ela.value))
					is Right -> f(ela.value).value

				}
			}
		}
		EitherT(v)

	}

	fun <B> map(F: Functor<F>): (((A) -> B) -> EitherT<F, L, B>) = { f ->
		val felb = F.run {
			value.map { ela ->
				EitherMonad<L>().run {
					ela.map(f).unwrap()
				}
			}
		}
		EitherT(felb)
	}
}

fun main(args: Array<String>) {
	val e1 = EitherT.pure<List.K, String, Int>(ListMonad)(1)
	val e2 = EitherT.pure<List.K, String, Int>(ListMonad)(2)
	val e3 = e1.flatMap<Int>(ListMonad)({ v1 ->
		e2.map<Int>(ListMonad)({ v2 ->
			v1 + v2
		})
	})
	println(e3.value)
}