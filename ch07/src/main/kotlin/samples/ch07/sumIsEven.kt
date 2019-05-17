package samples.ch07

/**
 * Created by prefert on 2019/5/13.
 */
val MutableList<Int>.sumIsEven: Boolean
    get() = this.sum() % 2 == 0

// sumIsEvenB 不能添加默认值
//val MutableList<Int>.sumIsEvenB: Boolean = false
//    get() = this.sum() % 2 == 0


fun main(args: Array<String>) {
    val list = mutableListOf(2,2,4)
    list.sumIsEven
}