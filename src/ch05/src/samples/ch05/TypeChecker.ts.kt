package ch05

/**
 * Created by prefert on 2019/5/13.
 */
fun typeCheckA(obj: Any) {
    if (obj is String) {
        print(obj.length)
    }
    if (obj !is String) { // 等同于 !(obj is String) print("Not a String")
    } else {
        print(obj.length)
    }
}

fun typeCheckB(obj: Any) {
    when (obj) {
        is String -> print(obj.length)
        else      -> print("Not a String")
    }
}


fun main(args: Array<String>) {
    typeCheckB(1024)
    typeCheckB("Prefer.t")
}