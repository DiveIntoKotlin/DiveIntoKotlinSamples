package samples.ch10

interface Eq<F> {
  fun F.eq(that: F): Boolean
}
