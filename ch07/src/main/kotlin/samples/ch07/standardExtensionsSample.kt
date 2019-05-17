package samples.ch07


/**
 * Created by prefert on 2019/5/13.
 */
data class Stu(val age: Int)

fun getStu(): Stu? {
    return Stu(21)
}

fun testRun() {
    val nickName = "Prefert"
    run {
        val nickName = "YarenTang"
        println(nickName) // YarenTang
    }
    println(nickName) // Prefert
}

fun testLet() {
    val student: Stu? = getStu()
    val result = student?.let {
        println(it.age) // 21
        it.age
    }
    println(result)
}

fun testAlso() {
    class Kot {
        val student: Stu? = getStu()
        var age = 0
        fun dealStu() {
            val result = student?.also { stu ->
                this.age += stu.age
                println(this.age) // 21
                println(stu.age) // 21
                this.age
            }
            println(result)
        }
    }
    Kot().dealStu()
}

fun testTakeIf() {
    val student: Stu? = getStu()
    val result = student?.takeIf {
        it.age > 18
    }
    println(result)
}

fun main(args: Array<String>) {
    testRun()
    testLet()
    testAlso()
    testTakeIf()
}