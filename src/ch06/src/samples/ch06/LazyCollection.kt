package samples.ch06

val list = listOf(1, 2, 3, 4, 5)
list.filter {it > 2}.map {it * 2}

list.asSequence().filter {it > 2}.map {it * 2}.toList()

list.asSequence().filter {
    println("filter($it)")
    it > 2
}.map {
    println("map($it)")
    it * 2
}

list.asSequence().filter {
    println("filter($it)")
    it > 2
}.map {
    println("map($it)")
    it * 2
}.toList()

list.filter {
    println("filter($it)")
    t > 2
}.map {
    println("map($it)")
    it * 2
}

val naturalNumList = generateSequence(0) { it + 1}

naturalNumList.takeWhile {it <= 9}.toList()