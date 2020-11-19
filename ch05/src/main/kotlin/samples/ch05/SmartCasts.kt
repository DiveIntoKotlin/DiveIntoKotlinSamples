package ch05

/**
 * Created by prefert on 2019/5/13.
 */

open class Human
data class Teacher(val name: String) : Human()

fun getStu(): Student? {
    return Student(null)
}

fun getTeacher(): Human? {
//    仅示例，实际存在可空的情况
//    return Teacher("jilen")
    return null
}

class Kot {
    private val stu: Student? = getStu() as? Student

    fun dealStuA() {
        if (stu != null) {
            print(stu.glasses)
        }
    }

    fun dealStu() {
        stu?.let { print(it.glasses) }
    }
}

fun main(args: Array<String>) {

    val stu: Any = Student(Glasses(189.00))
    if (stu is Student) println(stu.glasses)

    val teacher = getTeacher() as? Teacher

//    不可直接调用 name
//    teacher.name
    if (teacher !== null) {
        print(teacher.name)
    }

//    JAVA
//    Object stu = Student(Glasses(189.00))
//    if(stu instanceof Student) System.out.println(((Student)stu).glasses

}