@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> Kind<List.K, A>.unwrap(): List<A> =
		this as List<A>


sealed class List<out A> : Kind<List.K, A> {
	object K
}

object Nil : List<Nothing>()
data class Cons<A>(val head: A, val tail: List<A>) : List<A>()

object ListFunctor : Functor<List.K> {
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

fun main(args: Array<String>) {
	val v = ListFunctor.run {
		Cons(1, Nil).map { it + 1 }
	}

	println(ListShow.run {
		v.show()
	})

	println(IntListEq.run {
		v.eq(Cons(2, Nil))
	})
}