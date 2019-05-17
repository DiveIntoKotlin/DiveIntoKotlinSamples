package ch08

import kotlin.reflect.*
import kotlin.reflect.full.*

sealed class Nat {
    companion object {
        object Zero: Nat()
    }
    val Companion._0
    get() = Zero

    fun <A: Nat> Succ<A>.preceed(): A {
        return this.prev
    }
}

data class Succ<N : Nat>(val prev: N): Nat()

fun <A: Nat> Nat.plus(other: A): Nat {
    return when {
        other is Succ<*> -> Succ(this.plus(other.prev)) // a + S(b) = S(a + b)
        else -> this // a + 0 = a
    }
}

fun plusDemo() {
    val _0 = Nat.Companion.Zero
    val _1 = Succ(_0)
    val _2 = Succ(_1)
    val _3 = Succ(_2)
    println(_0.plus(_1) == _1)
    println(_1.plus(_2) == _3)
}

fun reflectDemo() {
    println(Nat.Companion::class.isCompanion)
    println(Nat::class.isSealed)
    println(Nat.Companion::class.objectInstance)
    println(Nat::class.companionObjectInstance)
    println(Nat::class.declaredMemberExtensionFunctions.map{it.name})
    println(Succ::class.declaredMemberExtensionFunctions.map{it.name})
    println(Succ::class.memberExtensionFunctions.map{it.name})
    println(Nat::class.declaredMemberExtensionProperties.map{it.name})
    println(Succ::class.declaredMemberExtensionProperties.map{it.name})
    println(Succ::class.memberExtensionProperties.map{it.name})
    println(Succ::class.starProjectedType)
}

fun main(args: Array<String>) {
    plusDemo()
    reflectDemo()
}
