package samples.ch07

/**
 * Created by prefert on 2019/5/13.
 */
open class Base

class Extended : Base()

fun Base.foo() = "I'm Base.foo!"
fun Extended.foo() = "I'm Extended.foo!"

open class X {
    open fun Base.foo() {
        println("I’m Base.foo in X")
    }

    open fun Extended.foo() {
        println("I’m Extended.foo in X")
    }

    fun deal(base: Base) {
        base.foo()
    }
}

class Y : X() {
    override fun Base.foo() {
        println("I’m Base.foo in Y")
    }

    override fun Extended.foo() {
        println("I’m Extended.foo in Y")
    }

}

fun main(args: Array<String>) {
    val instance: Base = Extended()
    val instance2 = Extended()
    println(instance.foo()) // I'm Base.foo!
    println(instance2.foo()) // I'm Extended.foo!

    X().deal(Base()) // 输出 I’m Base.foo in X
    Y().deal(Base()) // 输出I’m Base.foo in Y — 即 dispatch receiver 被动态处理
    X().deal(Extended()) // 输出I’m Base.foo in X — 即 extension receiver 被静态处理
    Y().deal(Extended()) // 输出I’m Base.foo in Y
}
