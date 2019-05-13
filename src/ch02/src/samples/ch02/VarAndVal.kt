package samples.ch02

fun main(args: Array<String>) {
	immutableRef()
	varAsProp()
	varAsSideEffect()
}

fun immutableRef() {
	val x = intArrayOf(1, 2, 3)
	// x = intArray(4, 5, 6) 编译报错
	x[0] = 2
	println(x[0])
}


fun varAsProp() {
	val book = Book("Thinking in Java") // 用val声明的book对象的引用不可变
	book.name = "Diving into Kotlin"
	book.printName() // Diving into Kotlin
}

//var或val定义在构造函数里时直接作为属性
class Book(var name: String) { // 用var声明的参数name引用可被改变
	fun printName() {
		println(this.name)
	}
}

var a = 1

fun count(x: Int) {
	a = a + 1
	println(x + a)
}

fun varAsSideEffect() {
	count(1)
	count(1)
}

fun calUseVar(list: List<Int>): Int {
	var res = 0
	for (el in list) {
		res *= el
		res += el
	}
	return res
}

fun calUseVal(list: List<Int>): Int {
	fun recurse(listr: List<Int>, res: Int): Int {
		if (listr.size > 0) {
			val el = listr.first()
			return recurse(listr.drop(1), res * el + el)
		} else {
			return res
		}
	}
	return recurse(list, 0)
}
