package ch05

/**
 * Created by prefert on 2019/5/13.
 */
sealed class Either<A, B>() {
    class Left<A, B>(val value: A) : Either<A, B>()
    class Right<A, B>(val value: B) : Either<A, B>()
}

fun getDegreeOfMyopiaKt(seat: Seat?): Either<Error, Double> {
    return seat?.student?.glasses?.let {
        Either.Right<Error,
                Double>(it.degreeOfMyopia)
    } ?: Either.Left<Error, Double>(Error("-1"))
}