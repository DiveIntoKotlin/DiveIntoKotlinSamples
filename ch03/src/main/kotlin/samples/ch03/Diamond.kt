package samples.ch03

interface Flyer1 {
	fun fly()
	fun kind() = "flying animals"
}

interface Animal {
	val name: String
	fun eat()
	fun kind() = "flying animals"
}

class Bird7(override val name: String) : Flyer1, Animal {
	override fun eat() {
		println("I can eat")
	}

	override fun fly() {
		println("I can fly")
	}

	override fun kind() = super<Flyer1>.kind()
}

open class Horse { //马
	fun runFast() {
		println("I can run fast")
	}
}

open class Donkey { //驴
	fun doLongTimeThing() {
		println("I can do some thing long time")
	}
}

class Mule {  //骡子
	fun runFast() {
		HorseC().runFast()
	}

	fun doLongTimeThing() {
		DonkeyC().doLongTimeThing()
	}

	private inner class HorseC : Horse()
	private inner class DonkeyC : Donkey()
}


interface CanFly {
	fun fly()
}

interface CanEat {
	fun eat()
}

open class Flyer2 : CanFly {
	override fun fly() {
		println("I can fly")
	}
}

open class Animal2 : CanEat {
	override fun eat() {
		println("I can eat")
	}
}

class Bird8(flyer: Flyer2, animal: Animal2) : CanFly by flyer, CanEat by animal {}

fun main(args: Array<String>) {
	val flyer = Flyer2()
	val animal = Animal2()
	val b = Bird8(flyer, animal)
	b.fly()
	b.eat()
}
