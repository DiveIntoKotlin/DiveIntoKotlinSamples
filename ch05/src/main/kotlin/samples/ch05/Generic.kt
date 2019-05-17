package samples.ch05

fun sum(a: Int, b: Int): Int {
    return a + b
}

class SmartList<T> : ArrayList<T> () {
    fun find(t: T): T? {
        val index = super.indexOf(t)
        return if (index >= 0) super.get(index) else null
    }
}

fun <T> ArrayList<T>.find(t: T): T? {
    val index = this.indexOf(t)
    return if (index >= 0) this.get(index) else null
}

class Plate<T>(val t: T)

open class Fruit(val weight: Double)

class Apple(weight: Double): Fruit(weight)
class Banana(weight: Double): Fruit(weight)

class FruitPlate<T: Fruit>(val t: T)

class Noodles(weight: Double)

interface Ground{}

class Watermelon(weight: Double): Fruit(weight), Ground

fun <T> cut(t: T) where T: Fruit, T: Ground {
    print("You can cut me")
}

fun main(args: Array<String>) {
    val smartList = SmartList<String>()
    smartList.add("one")
    println(smartList.find("one"))
    println(smartList.find("two").isNullOrEmpty())

    val arrayList = ArrayList<String>()
    arrayList.add("one")
    println(arrayList.find("one"))
    println(arrayList.find("two").isNullOrEmpty())

    val applePlate = FruitPlate<Apple>(Apple(100.0))

    // val noodlesPlate = FruitPlate<Noodles>(Noodles(100.0)) 不允许

    cut(Watermelon(3.0))

    // cut(Apple(2.0)) 不允许
}

