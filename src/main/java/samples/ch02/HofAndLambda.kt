package samples.ch02

data class Country(
		val name: String,
		val continient: String,
		val population: Int)

class CountryApp {
	fun filterCountries(
			countries: List<Country>,
			test: (Country) -> Boolean): List<Country> // 增加了一个函数类型的参数test
	{
		val res = mutableListOf<Country>()
		for (c in countries) {
			if (test(c)) { // 直接调用test来进行筛选
				res.add(c)
			}
		}
		return res
	}
}


class CountryTest {
	fun isBigEuropeanCountry(country: Country): Boolean {
		return country.continient == "EU" && country.population > 10000
	}
}

fun countryFilterTest() {
	val countryApp = CountryApp()
	val countryTest = CountryTest()
	val countries = listOf(Country("China", "Asia", 1300000000))

	countryApp.filterCountries(countries, countryTest::isBigEuropeanCountry)
	countryApp.filterCountries(countries, fun(country: Country): Boolean {
		return country.continient == "EU" && country.population > 10000
	})
	countryApp.filterCountries(countries, { country ->
		country.continient == "EU" && country.population > 10000
	})
}

fun lambdaDef() {
	val sum0: (Int, Int) -> Int = { x: Int, y: Int ->
		x + y
	}
	val sum1 = { x: Int, y: Int ->
		x + y
	}

	val sum2: (Int, Int) -> Int = { x, y ->
		x + y
	}

	println(sum0(1, 1))
	println(sum1(1, 1))
	println(sum2(1, 1))
}

fun funInvoke() {
	fun foo(i: Int) = {
		print(i)
	}
	listOf(1, 2, 3).forEach { foo(it) }
	listOf(1, 2, 3).forEach { foo(it).invoke() }
	listOf(1, 2, 3).forEach { foo(it)() }
}

fun selfRunLambda() {
	{ x: Int -> println(x) }(0)
}

fun curryLike() {
	fun <A, B> Array<A>.corresponds(that: Array<B>, p: (A, B) -> Boolean): Boolean {
		val i = this.iterator()
		val j = that.iterator()
		while (i.hasNext() && j.hasNext()) {
			if (!p(i.next(), j.next())) {
				return false
			}
		}
		return !i.hasNext() && !j.hasNext()
	}

	val a = arrayOf(1, 2, 3)
	val b = arrayOf(2, 3, 4)
	a.corresponds(b) { x, y -> x + 1 == y } // true
	a.corresponds(b) { x, y -> x + 2 == y } // false
}

fun main(args: Array<String>) {
	countryFilterTest()
	funInvoke()
	selfRunLambda()
}

