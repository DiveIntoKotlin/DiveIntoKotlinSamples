package samples.ch10

interface Kind<out F, out A>

typealias Kind2<F, A, B> = Kind<Kind<F, A>, B>
