package samples.ch10

interface Show<F> {
  fun <A> Kind<F, A>.show(): String
}
