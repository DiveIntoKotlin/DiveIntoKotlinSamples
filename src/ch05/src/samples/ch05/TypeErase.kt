package samples.ch05

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class PlateA<T>(val t: T, val clazz: Class<T>) {
    fun getType() {
        println(clazz)
    }
}

open class GenericsToken<T> {
    var type: Type = Any::class.java
    init {
        val superClass = this.javaClass.genericSuperclass
        type = (superClass as ParameterizedType).getActualTypeArguments()[0]
    }
}

fun main(args: Array<String>) {
    val appleList = ArrayList<Apple>()
    println(appleList.javaClass)

    val appleArray = arrayOfNulls<Apple>(3)
    // val anyArray: Array<Any?> = appleArray 不允许

    val applePlateA = PlateA(Apple(1.0), Apple::class.java)
    println(applePlateA.getType())

    // val listType = ArrayList<String>::class.java 不被允许

    val list1 = ArrayList<String>()
    val list2 = object : ArrayList<String>() {}
    println(list1.javaClass.genericSuperclass)
    println(list2.javaClass.genericSuperclass)

    val gt = object : GenericsToken<Map<String, String>>() {}
    println(gt.type)

}