package samples.ch02

fun ifExpression(flag: Boolean) {
	val a = if (flag) "dive into Kotlin" else ""
	println(a.toUpperCase())
}

fun ifWithSideEffect() {
	var a = 1
	fun foo() = if (a > 0) {
		a = 2 // 副作用，a的值变化了
		a
	} else 0
}

data class Result(val success: Boolean, val response: String)
class JsonDecodeException : Exception()

fun jsonDecode(body: String): Int {
	println(body)
	TODO()
}


fun tryExpression() {
	val result: Result = Result(true, "1")
	val res: Int? = try {
		if (result.success) {
			jsonDecode(result.response)
		} else null
	} catch (e: JsonDecodeException) {
		null
	}
	println(res)
}

// 枚举和When表达式
enum class DayOfWeek(val day: Int) {
	MON(1),
	TUE(2),
	WEN(3),
	THU(4),
	FRI(5),
	SAT(6),
	SUN(7)
	;  // 如果以下有额外的方法或属性定义，则必须强制加上分号

	fun getDayNumber(): Int {
		return day
	}
}

enum class Day {
	MON,
	TUE,
	WEN,
	THU,
	FRI,
	SAT,
	SUN
}


fun enumAndWhen() {
	fun basketball() {
		TODO()
	}

	fun fishing() {
		TODO()
	}

	fun appointment() {
		TODO()
	}

	fun library() {
		TODO()
	}

	fun study() {
		TODO()
	}

	fun scheduleUseIf(day: Day, sunny: Boolean) = {
		if (day == Day.SAT) {
			basketball()
		} else if (day == Day.SUN) {
			fishing()
		} else if (day == Day.FRI) {
			appointment()
		} else {
			if (sunny) {
				library()
			} else {
				study()
			}
		}
	}

	fun scheduleUseWhen0(sunny: Boolean, day: Day) = when (day) {
		Day.SAT -> basketball()
		Day.SUN -> fishing()
		Day.FRI -> appointment()
		else -> when {
			sunny -> library()
			else -> study()
		}
	}

	fun scheduleUseWhen1(sunny: Boolean, day: Day) = when {
		day == Day.SAT -> basketball()
		day == Day.SUN -> fishing()
		day == Day.FRI -> appointment()
		sunny -> library()
		else -> study()
	}
}

// for和范围表达式
fun forAndRange() {
	for (i in 1..10) println(i)
	for (i in 1..10 step 2) println(i)
	for (i in 10 downTo 1 step 2) println(i)
	"kot" in "abc".."xyz"
}

//中缀表达式
class Person {
	infix fun called(name: String) {
		println("My name is ${name}.")
	}
}

fun infixFun() {
	val p = Person()
	p called "Shaw"
}

// 字符串操作

fun stringOp() {
	val s = "hello"
	val rawString = """
    \n Kotlin is awesome.
    \n Kotlin is a better Java."""
	val html =
			"""<html>
                       <body>
                           <p>Hello World.</p>
                       </body>
                   </html>
                """

	fun message(name: String, lang: String) = "Hi " + name + ", welcome to " + lang + "!"

	println(s)
	println(rawString)
	println(html)
	println(message("Yison", "Kotlin"))
}

