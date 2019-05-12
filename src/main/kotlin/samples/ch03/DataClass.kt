package samples.ch03

data class Bird9(var weight: Double, var age: Int, var color: String)

fun main(args: Array<String>) {
    val b1 = Bird9(weight = 1000.0, age= 1, color = "blue")
    val b2 = Bird9(weight = 1000.0, age= 1, color = "blue")
    println(b1.equals(b2))
    println(b1 == b2)

    b1.copy(age = 2)

    val (weight, age, color) = b1
    println("weight: $weight, age: $age, color: $color")

    val birdInfo = "20.0,1,blue"
    val (weightA, ageA, colorA) = birdInfo.split(",")
    println("weightA: $weightA, ageA: $ageA, colorA: $colorA")

    val pair = Pair(20.0,1)
    val triple = Triple(20.0, 1,"blue")

    val (weightP, ageP) = pair
    println("weightP: $weightP, ageP: $ageP")

    val (weightT, ageT, colorT) = triple
    println("weightT: $weightT, ageT: $ageT, colorT: $colorT")
}