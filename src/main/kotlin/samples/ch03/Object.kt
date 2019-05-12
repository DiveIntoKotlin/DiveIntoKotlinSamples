package samples.ch03

import java.util.*

class Prize0(val name: String, val count: Int, val type: Int) {
    companion object {
        val TYPE_REDPACK = 0
        val TYPE_COUPON = 1

        fun isRedpack(prize: Prize0): Boolean {
            return prize.type == TYPE_REDPACK
        }
    }
}

class Prize1 private constructor(val name: String, val count: Int, val type: Int) {
    companion object {
        val TYPE_COMMON = 1
        val TYPE_REDPACK = 2
        val TYPE_COUPON = 3


        val defaultCommonPrize = Prize1("普通奖品", 10, Prize1.TYPE_COMMON)

        fun newRedpackPrize(name: String, count: Int) = Prize1(name, count, Prize1.TYPE_REDPACK)
        fun newCouponPrize(name: String, count: Int) = Prize1(name, count, Prize1.TYPE_COUPON)
        fun defaultCommonPrize() = defaultCommonPrize
    }
}

object DatabaseConfig {
    var host: String = "127.0.0.1"
    var port: Int = 3306
    var username: String = "root"
    var password: String = ""
}

val comparator0 = object : Comparator<String> {
    override fun compare(s1: String?, s2: String?): Int {
        if (s1 == null)
            return -1
        else if (s2 == null)
            return 1
        return s1.compareTo(s2)
    }
}

val comparator1 = Comparator<String> { s1, s2 ->
    if (s1 == null)
        return@Comparator -1
    else if (s2 == null)
        return@Comparator 1
    s1.compareTo(s2)
}


fun main(args: Array<String>) {
    val prize = Prize0("红包", 10, Prize0.TYPE_REDPACK)
    print(Prize0.isRedpack(prize))

    val redpackPrize = Prize1.newRedpackPrize("红包", 10)
    val couponPrize = Prize1.newCouponPrize("十元代金券", 10)
    val commonPrize = Prize1.defaultCommonPrize()

    val list = listOf("redpack", "score", "card")
    Collections.sort(list, comparator0)
    Collections.sort(list, comparator1)
}