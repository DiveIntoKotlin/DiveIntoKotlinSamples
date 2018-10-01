interface Foldable<F> {
	fun <A, B> Kind<F, A>.fold(init: B): ((B, A) -> B) -> B
}