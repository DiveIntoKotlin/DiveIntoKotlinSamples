package samples.ch03

class Bird0 {
	val weight: Double = 500.0
	val color: String = "blue"
	val age: Int = 1
	fun fly() {} // 全局可见
}

class Bird1(val weight: Double = 0.00, val age: Int = 0, val color: String = "blue")

class Bird2(val weight: Double = 0.00, val age: Int = 0, val color: String = "blue") {
	init {
		println("do some other things")
		println("the weight is ${this.weight}")
	}
}

class Bird3(weight: Double, age: Int, color: String) {
	val weight: Double
	val age: Int
	val color: String

	init {
		this.weight = weight
		println("The bird's weight is ${this.weight}.")
		this.age = age
		println("The bird's age is ${this.age}.")
	}

	init {
		this.color = color
		println("The bird's age is ${this.color}.")
	}
}

class Bird4(val weight: Double, val age: Int, val color: String) {
	val sex: String by lazy {
		if (color == "yellow") "male" else "female"
	}
	val sex1: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
		//并行模式
		if (color == "yellow") "male" else "female"
	}
	val sex2: String by lazy(LazyThreadSafetyMode.NONE) {
		//不做任何线程保证也不会有任何线程开销
		if (color == "yellow") "male" else "female"
	}
}

class Bird5(val weight: Double, val age: Int, val color: String) {
	lateinit var sex: String // sex 可以延迟初始化

	fun printSex() {
		this.sex = if (this.color == "yellow") "male" else "female"
		println(this.sex)
	}
}

interface Flyer {
	val speed: Int
	fun kind()
	fun fly() {
		println("I can fly")
	}
}


fun main(args: Array<String>) {
	val bird = Bird1(1000.0, 2, "bule")
	println(bird)
}