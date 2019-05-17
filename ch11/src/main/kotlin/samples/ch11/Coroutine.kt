package samples.ch11

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    GlobalScope.launch { // 在后台启动一个协程
        delay(1000L) // 延迟一秒(非阻塞)
        println("World!") // 延迟之后输出
    }
    println("Hello,") // 协程被延迟了一秒，但是主线程继续执行
    Thread.sleep(2000L) // 为了使JVM保活，阻塞主线程2秒钟(这段代码删掉会出现什么情况？？？)
}

fun main2(args: Array<String>) = runBlocking {
    repeat(100_000) {
        launch {
            println("Hello")
        }
    }
}

fun main3(args: Array<String>) {
    launch { // 在后台启动一个协程
        delay(1000L) // 延迟一秒(非阻塞)
        println("World!") // 延迟之后输出
    }
    println("Hello,") // 协程被延迟了一秒，但是主线程继续执行
    Thread.sleep(2000L) // 为了使JVM保活，阻塞主线程2秒钟(这段代码删掉会出现什么情况？？？)
}

fun main4(args: Array<String>) = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    delay(2000L)
}

fun main5(args: Array<String>) = runBlocking {
    val job = launch {
        search()
    }
    println("Hello,")
    job.join()
}

suspend fun search() {
    delay(1000L)
    println("World!")
}

suspend fun searchItemlOne(): String {
    delay(1000L)
    return "item-one"
}

suspend fun searchItemTwo(): String {
    delay(1000L)
    return "item-two"
}

fun main6(args: Array<String>) = runBlocking<Unit> {
    val one = searchItemlOne()
    val two = searchItemTwo()
    println("The items is ${one} and ${two}")
}

fun main7(args: Array<String>) = runBlocking<Unit> {
    val one = async { searchItemlOne() }
    val two = async { searchItemTwo() }
    println("The items is ${one.await()} and ${two.await()}")
}

val time = measureTimeMillis {
    val one = searchItemlOne()
    val two = searchItemTwo()
    println("The items is ${one} and ${two}")
}
println("Cost time is ${time} ms")

val time = measureTimeMillis {
    val one = async { searchItemlOne() }
    val two = async { searchItemTwo() }
    println("The items is ${one.await()} and ${two.await()}")
}

println("Cost time is ${time} ms")