package ch07

/**
 * Created by prefert on 2019/5/13.
 */
class SonA {
    fun foo() = println("son called member foo")
}

fun SonA.foo() = println("son called extention foo")

fun main(args: Array<String>) {
    SonA().foo() // son called member foo
}

