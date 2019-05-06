interface Monoid<A> {
	fun zero(): A
	fun A.append(b: A): A
}