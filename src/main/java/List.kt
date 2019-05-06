@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> Kind<List.K, A>.unwrap(): List<A> =
		this as List<A>


sealed class List<out A> : Kind<List.K, A> {
	object K
}

object Nil : List<Nothing>()
data class Cons<A>(val head: A, val tail: List<A>) : List<A>()

interface IListFunctor : Functor<List.K> {
	override fun <A, B> Kind<List.K, A>.map(f: (A) -> B): Kind<List.K, B> {
		return when (this) {
			is Cons -> {
				val t = this.tail.map(f).unwrap()
				Cons<B>(f(this.head), t).unwrap()
			}
			else -> Nil
		}
	}
}

object ListFunctor : IListFunctor

object ListShow : Show<List.K> {
	override fun <A> Kind<List.K, A>.show(): String {
		val fa = this
		return "[" + ListFoldable.run {
			fa.fold("")({ r, i ->
				r + i + ","
			}) + "]"
		}
	}
}

object ListFoldable : Foldable<List.K> {
	override fun <A, B> Kind<List.K, A>.fold(init: B): ((B, A) -> B) -> B = { f ->
		fun fold0(l: List<A>, v: B): B {
			return when (l) {
				is Cons -> {
					fold0(l.tail, f(v, l.head))
				}
				else -> v
			}
		}
		fold0(this.unwrap(), init)


	}
}

abstract class ListEq<A>(val a: Eq<A>) : Eq<Kind<List.K, A>> {
	override fun Kind<List.K, A>.eq(that: Kind<List.K, A>): Boolean {
		val curr = this
		return if (curr is Cons && that is Cons) {
			val headEq = a.run {
				curr.head.eq(that.head)
			}
			if (headEq) curr.tail.eq(that.tail) else false
		} else if (curr is Nil && that is Nil) {
			true
		} else false
	}
}

object IntEq : Eq<Int> {
	override fun Int.eq(that: Int): Boolean {
		return this == that
	}
}

object IntListEq : ListEq<Int>(IntEq)

object ListMonad : Monad<List.K> {

	private fun <A> append(fa: Kind<List.K, A>, fb: Kind<List.K, A>): Kind<List.K, A> {
		return if (fa is Cons) {
			Cons(fa.head, append(fa.tail, fb).unwrap())
		} else {
			fb
		}
	}

	override fun <A> pure(a: A): Kind<List.K, A> {
		return Cons(a, Nil)
	}

	override fun <A, B> Kind<List.K, A>.flatMap(f: (A) -> Kind<List.K, B>): Kind<List.K, B> {
		val fa = this
		val empty: Kind<List.K, B> = Nil
		return ListFoldable.run {
			fa.fold(empty)({ r, l ->
				append(r, f(l))
			}
			)
		}
	}
}

object IntAddMonoid : Monoid<Int> {
	override fun zero(): Int {
		return 0
	}

	override fun Int.append(b: Int): Int {
		return this + b
	}
}

fun <A> List<A>.sum(ma: Monoid<A>): A {
	val fa = this
	return ListFoldable.run {
		fa.fold(ma.zero())({ s, i ->
			ma.run {
				s.append(i)
			}
		})
	}
}


fun main(args: Array<String>) {
	val v = ListFunctor.run {
		Cons(1, Nil).map { it + 1 }
	}.unwrap()

	println(ListShow.run {
		v.show()
	})

	println(IntListEq.run {
		v.eq(Cons(2, Nil))
	})
	println(v.sum(IntAddMonoid))
}