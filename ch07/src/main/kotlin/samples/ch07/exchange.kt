package samples.ch07

/**
 * Created by prefert on 2019/5/13.
 */
fun MutableList<Int>.exchange(fromIndex: Int, toIndex: Int) {
    val tmp = this[fromIndex]
    this[fromIndex] = this[toIndex]
    this[toIndex] = tmp
}

fun main(args: Array<String>) {
    val list = mutableListOf(1, 2, 3)
    list.exchange(1, 2)
}