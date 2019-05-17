package ch05

/**
 * Created by prefert on 2019/5/13.
 */
abstract class Animal(val weight: Double)
class Fish(weight: Double, val swimmingSpeed: Double): Animal(weight)


interface ICanFly
interface ICanBuildNest
class Bird(weight: Double, flightSpeed: Double): Animal(weight), ICanFly,
    ICanBuildNest

fun main(args: Array<String>) {
    var birdA: Animal = Bird(weight = 0.1, flightSpeed = 15.0)
    birdA = Fish(weight = 0.15, swimmingSpeed = 10.0)

    val birdB = Bird(weight = 0.1, flightSpeed = 15.0)
    val animal: Animal = birdB
    // Error: Type mismatch: inferred type is Animal but Bird was expected
//    val b2: Bird = animal
}