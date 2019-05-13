package ch05

/**
 * Created by prefert on 2019/5/13.
 */

fun getStu(): Student? {
    return Student(null)
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

//    JAVA
//    Object stu = Student(Glasses(189.00))
//    if(stu instanceof Student) System.out.println(((Student)stu).glasses

}