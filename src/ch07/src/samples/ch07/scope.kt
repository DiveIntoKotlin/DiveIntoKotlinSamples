package ch07

/**
 * Created by prefert on 2019/5/13.
 */
class SonC {
    fun foo() {
        println("foo in Class Son")
    }
}

object Parent {
    fun foo() {
        println("foo in Class Parent")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        fun SonC.foo2() {
            this.foo()  // foo in Class Son
            this@Parent.foo() // foo in Class Parent
        }
        SonC().foo2()
    }
}
