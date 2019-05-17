package ch05

/**
 * Created by prefert on 2019/5/13.
 */
data class Seat(val student: Student?)
data class Student(val glasses: Glasses?)
data class Glasses(val degreeOfMyopia: Double)
