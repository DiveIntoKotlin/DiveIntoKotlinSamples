package ch07

/**
 * Created by prefert on 2019/5/13.
 */
interface Sumable<T> {
    fun plusThat(that: T): T
}

data class Len(val v: Int) : Sumable<Len> {
    override fun plusThat(that: Len) = Len(this.v + that.v)
}

data class Area(val value: Double)

operator fun Area.plus(that: Area): Area {
    return Area(this.value + that.value)
}

fun main(args: Array<String>) {
    println(Area(1.0) + Area(2.0)) // 运行结果: Area(value=3.0)
}