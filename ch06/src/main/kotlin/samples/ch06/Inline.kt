package samples.ch06

fun main(args: Array<String>) {
    foo {
        println("dive into Kotlin...")
    }
}
fun foo(block: () -> Unit) {
    println("before block")
    block()
    println("end block")
}

public static final void main(@NotNull String[] args) {
    Intrinsics.checkParameterIsNotNull(args, "args");
    foo((Function0)null.INSTANCE);
}

public static final void foo(@NotNull Function0 block) {
    Intrinsics.checkParameterIsNotNull(block, "block");
    String var1 = "before block";
    System.out.println(var1);
    block.invoke();
    var1 = "end block";
    System.out.println(var1);
}

inline fun foo(block: () -> Unit) {
    println("before block")
    block()
    println("end block")
}

public static final void main(@NotNull String[] args) {
    Intrinsics.checkParameterIsNotNull(args, "args");
    String var1 = "before block";
    System.out.println(var1);
    // block函数体在这里开始粘贴
    String var2 = "dive into Kotlin...";
    System.out.println(var2);
    // block函数体在这里结束粘贴
    var1 = "end block";
    System.out.println(var1);
}

public static final void foo(@NotNull Function0 block) {
    Intrinsics.checkParameterIsNotNull(block, "block");
    String var2 = "before block";
    System.out.println(var2);
    block.invoke();
    var2 = "end block";
    System.out.println(var2);
}

inline fun <T, R> Array<out T>.map(
        transform: (T) -> R
): List<R>

inline fun <T> Array<out T>.filter(
        predicate: (T) -> Boolean
): List<T>

fun main(args: Array<String>) {
    foo ({
        println("I am inlined...")
    }, {
        println("I am not inlined...")
    })
}
inline fun foo(block1: () -> Unit, noinline block2: () -> Unit) {
    println("before block")
    block1()
    block2()
    println("end block")
}

public static final void main(@NotNull String[] args) {
    Intrinsics.checkParameterIsNotNull(args, "args");
    Function0 block2$iv = (Function0)null.INSTANCE;
    String var2 = "before block";
    System.out.println(var2);
    // block1 被内联了
    String var3 = "I am inlined...";
    System.out.println(var3);
    // block2 还是原样
    block2$iv.invoke();
    var2 = "end block";
    System.out.println(var2);
}

public static final void foo(@NotNull Function0 block1, @NotNull Function0 block2) {
    Intrinsics.checkParameterIsNotNull(block1, "block1");
    Intrinsics.checkParameterIsNotNull(block2, "block2");
    String var3 = "before block";
    System.out.println(var3);
    block1.invoke();
    block2.invoke();
    var3 = "end block";
    System.out.println(var3);
}

fun main(args: Array<String>) {
    foo()

}
fun localReturn() {
    return
}
fun foo() {
    println("before local return")
    localReturn()
    println("after local return")
    return
}

fun main(args: Array<String>) {
    foo { return }
}
fun foo(returning: () -> Unit) {
    println("before local return")
    returning()
    println("after local return")
    return
}

fun main(args: Array<String>) {
    foo { return }
}
inline fun foo(returning: () -> Unit) {
    println("before local return")
    returning()
    println("after local return")
    return
}

fun main(args: Array<String>) {
    foo { return@foo }
}
fun foo(returning: () -> Unit) {
    println("before local return")
    returning()
    println("after local return")
    return
}

fun hasZeros(list: List<Int>): Boolean {
    list.forEach {
        if (it == 0) return true // 直接返回hasZeros函数结果
    }
    return false
}

fun main(args: Array<String>) {
    foo { return }
}
inline fun foo(crossinline returning: () -> Unit) {
    println("before local return")
    returning()
    println("after local return")
    return
}

fun main(args: Array<String>) {
    getType<Int>()
}

inline fun <reified T> getType() {
    print(T::class)
}

inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}