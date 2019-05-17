package samples.ch05

val doubleComparator = Comparator<Double> {
    d1, d2 -> d1.compareTo(d2)
}

val numberComparator = Comparator<Number> {
    n1, n2 -> n1.toDouble().compareTo(n2.toDouble())
}

fun copy(dest: Array<Double?>, src: Array<Double>) {
    if (dest.size < src.size) {
        throw IndexOutOfBoundsException()
    } else {
        src.forEachIndexed {index, _ -> dest[index] = src[index]}
    }
}

fun <T> copy(dest: Array<T?>, src: Array<T>) {
    if (dest.size < src.size) {
        throw IndexOutOfBoundsException()
    } else {
        src.forEachIndexed {index, _ -> dest[index] = src[index]}
    }
}

fun <T> copyIn(dest: Array<in T>, src: Array<T>) {
    if (dest.size < src.size) {
        throw IndexOutOfBoundsException()
    } else {
        src.forEachIndexed {index, _ -> dest[index] = src[index]}
    }
}

fun <T> copyOut(dest: Array<T>, src: Array<out T>) {
    if (dest.size < src.size) {
        throw IndexOutOfBoundsException()
    } else {
        src.forEachIndexed {index, _ -> dest[index] = src[index]}
    }
}

fun main(args: Array<String>) {
    val doubleList = mutableListOf(2.0,3.0)
    doubleList.sortWith(doubleComparator)

    doubleList.sortWith(numberComparator)

    val intList = mutableListOf(1,2)
    intList.sortWith(numberComparator)

    var dest = arrayOfNulls<Double>(3)
    val src = arrayOf(1.0,2.0,3.0)
    copy(dest, src)

    var destDouble = arrayOfNulls<Double>(3)
    val srcDouble = arrayOf(1.0,2.0,3.0)
    copy(destDouble, srcDouble)

    var destInt = arrayOfNulls<Int>(3)
    val srcInt = arrayOf(1,2,3)
    copy(destInt, srcInt)

    copyIn(dest, src)
    copyOut(dest, src)

}