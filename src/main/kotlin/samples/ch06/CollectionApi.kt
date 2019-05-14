package samples.ch06

int list[] = {1, 2, 3, 4, 5, 6}
int newList[] = new int[list.length]
for (int i = 0; i < list.length; i++) {
    newList[i] = list[i] * 2
}

val list1 = listOf(1, 2, 3, 4, 5, 6)
val newList1 = list1.map {it * 2}

public inline fun <T, R> Array<out T>.map(transform: (T) -> R): List<R> {
    return mapTo(ArrayList<R>(size), transform)
}
public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.mapTo(destination: C, transform: (T) -> R): C {
    for (item in this)
        destination.add(transform(item))
    return destination
}

data class Student (val name: String, val age: Int, val sex: String, val score: Int)
val jilen = Student("Jilen", 30, "m", 85)
val shaw = Student("Shaw", 18, "m", 90)
val yison = Student("Yison", 40, "f", 59)
val jack = Student("Jack", 30, "m", 70)
val lisa = Student("Lisa", 25, "f", 88)
val pan = Student("Pan", 36, "f", 55)
val students = listOf(jilen, shaw, yison, jack, lisa, pan)

val mStudents = students.filter {it.sex == "m"}

public inline fun <T> Array<out T>.filter(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList<T>(), predicate)
}
public inline fun <T, C : MutableCollection<in T>> Array<out T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
    for (element in this) if (predicate(element)) destination.add(element)
    return destination
}

val fStudents = students.filterNot {it.sex == "m"}

val countMStudent = students.count {it.sex == "m"}
val countFStudent = students.count {it.sex == "f"}

val countMStudent = students.filter {it.sex == "m"}.size
val countFStudent = students.filter {it.sex == "f"}.size

var scoreTotal = 0
for (item in students) {
    scoreTotal = scoreTotal + item.score
}
val scoreTotal1 = students.sumBy {it.score}

val a = listOf(1, 2, 3, 4, 5)
val b = listOf(1.1, 2.5, 3.0, 4.5)
val aTotal = a.sum()
val bTotal = b.sum()

val aTotal = a.sumBy {it}
val bTotal = b.sumBy {it}

public inline fun <T, R> Array<out T>.fold(initial: R, operation: (acc: R, T) -> R): R {
    var accumulator = initial
    for (element in this) accumulator = operation(accumulator, element)
    return accumulator
}

val scoreTotal = students.fold(0) {accumulator, student -> accumulator + student.score}
{accumulator, student -> accumulator + student.score}

var accumulator = 0
for (student in students) accumulator = accumulator + student.score

public inline fun <S, T : S> Array<out T>.reduce(operation: (acc: S, T) -> S): S {
    if (isEmpty())
        throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator: S = this[0]
    for (index in 1..lastIndex) {
        accumulator = operation(accumulator, this[index])
    }
    return accumulator
}

val scoreTotal = students.reduce {accumulator, student -> accumulator + student.score}

val list = listOf(listOf(jilen, shaw, lisa), listOf(yison, pan), listOf(jack))
val newList = listOf(jilen, shaw, lisa, yison, pan, jack)
list.flatten()

public fun <T> Array<out Array<out T>>.flatten(): List<T> {
    val result = ArrayList<T>(sumBy { it.size })
    for (element in this) {
        result.addAll(element)
    }
    return result
}

data class Student (val name: String, val age: Int, val sex: String, val score: Int, val hobbies: List<String>)
val jilen = Student("Jilen", 30, "m", 85, listOf("coding", "reading"))
val shaw = Student("Shaw", 18, "m", 90, listOf("drinking", "fishing"))
val yison = Student("Yison", 40, "f", 59, listOf("running", "game"))
val jack = Student("Jack", 30, "m", 70, listOf("drawing"))
val lisa = Student("Lisa", 25, "f", 88, listOf("writing"))
val pan = Student("Pan", 36, "f", 55, listOf("dancing"))
val students = listOf(jilen, shaw, yison, jack, lisa, pan)

students.map {it.hobbies}.flatten()
students.flatMap {it.hobbies}

public inline fun <T, R> Array<out T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
    return flatMapTo(ArrayList<R>(), transform)
}

public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C {
    for (element in this) {
        val list = transform(element)
        destination.addAll(list)
    }
    return destination
}



