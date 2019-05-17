package samples.ch04

sealed class Shape {
	class Circle(val radius: Double) : Shape()
	class Rectangle(val width: Double, val height: Double) : Shape()
	class Triangle(val base: Double, val height: Double) : Shape()
}

fun getArea(shape: Shape): Double = when (shape) {
	is Shape.Circle -> Math.PI * shape.radius * shape.radius
	is Shape.Rectangle -> shape.width * shape.height
	is Shape.Triangle -> shape.base * shape.height / 2.0
}


