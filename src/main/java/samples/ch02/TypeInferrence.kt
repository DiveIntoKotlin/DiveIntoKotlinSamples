package samples.ch02

fun main(args: Array<String>) {
	varInfers()
}

fun varInfers() {
	val string = "I am Kotlin"
	val int = 1314
	val long = 1314L
	val float = 13.14f
	val double = 13.34
	val double2 = 10.1e6

	val vars = listOf(string, int, long, float, double, double2)
	vars.forEach { println(it.javaClass.name) }
}

fun sum0(x: Int, y: Int): Int {
	return x + y
}

//返回值类型自动推导
fun sum1(x: Int, y: Int) = x + y

//递归函数需指定返回值类型
fun fib(i: Int): Int {
	return when {
		i == 0 -> 0
		i == 1 -> 1
		else -> fib(i - 1) + fib(i - 2)
	}
}