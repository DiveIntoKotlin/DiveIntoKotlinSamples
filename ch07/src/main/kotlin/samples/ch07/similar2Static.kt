package samples.ch07

/**
 * Created by prefert on 2019/5/13.
 */
class Son {
    companion object {
        val age = 10 }
}

fun Son.Companion.foo() {
    println("age = $age")
}

fun main(args: Array<String>) {
    Son.foo()
}

