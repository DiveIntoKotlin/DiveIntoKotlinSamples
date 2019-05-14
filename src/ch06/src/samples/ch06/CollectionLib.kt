package samples.ch06

fun merge(a: List<Int>, b: MutableList<Int>) = {
    for (item in a) {
        b.add(item)
    }
}

public static List<Int> foo(List<Int> list) = {
    for (int i = 0; i < list.size(); i++) {
        list[i] = list[i] * 2;
    }
    return list;
}

fun bar(list: List<Int>) {
    println(foo(list))
}