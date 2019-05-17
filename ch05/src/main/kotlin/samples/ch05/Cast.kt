package ch05

/**
 * Created by prefert on 2019/5/13.
 */
fun <T> castA(original: Any): T? = original as? T

inline fun <reified T> cast(original: Any): T? = original as? T


fun main(args: Array<String>) {
    // java.lang.Long cannot be cast to java.lang.String
//    val ansA = castA<String>(140163L)

    val ans = cast<String>(140163L)

    println(ans)
}