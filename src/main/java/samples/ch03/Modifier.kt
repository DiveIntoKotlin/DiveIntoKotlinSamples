package samples.ch03


open class Bird6 {
	open fun fly() {
		println("I can fly.")
	}
}

class Penguin : Bird6() {
	override fun fly() {
		println("I can't fly actually.")
	}
}


private class BZEngine(type: String) : Engine(type) {
	override fun engineType(): String {
		return super.engineType()  //ok
	}
}

private open class Engine(val type: String) {
	protected open fun engineType(): String {
		return "the engine type is $type"
	}
}
