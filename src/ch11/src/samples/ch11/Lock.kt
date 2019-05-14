package samples.ch11

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

class Shop0 {
    val goods = hashMapOf<Long,Int>()

    init {
        goods.put(1,10)
        goods.put(2,15)
    }

    fun buyGoods(id: Long) {
        val stock = goods.getValue(id)
        goods.put(id, stock - 1)
    }

    @Synchronized fun buyGoods1(id: Long) {
        val stock = goods.getValue(id)
        goods.put(id, stock - 1)
    }

    fun buyGoods2(id: Long) {
        synchronized(this) {
            val stock = goods.getValue(id)
            goods.put(id, stock - 1)
        }
    }

    val lock = ReentrantLock()

    fun buyGoods3(id: Long) {
        lock.lock()
        try {
            val stock = goods.getValue(id)
            goods.put(id, stock - 1)
        } catch (ex: Exception) {
            println("[Exception] is $ex")
        } finally {
            lock.unlock()
        }
    }

    fun buyGoods4(id: Long) {
        withLock(lock, {buyGoods(4)})
    }
}

class Shop (private var goods: HashMap<Long, Int>) {
    private val lock: Lock = ReentrantLock()

    fun buyGoods(id: Long) {
        lock.withLock {
            val stock = goods.getValue(id)
            goods.put(id, stock - 1)
        }
    }
}

class ShopApi {
    private val A_goods = hashMapOf<Long,Int>()
    private val B_goods = hashMapOf<Long,Int>()

    private var shopA: Shop
    private var shopB: Shop

    init {
        A_goods.put(1,10)
        A_goods.put(2,15)
        B_goods.put(1,20)
        B_goods.put(2,10)
        shopA = Shop(A_goods)
        shopB = Shop(B_goods)
    }

    fun buyGoods(shopName: String, id: Long) {
        when (shopName) {
            "A" -> shopA.buyGoods(id)
            "B" -> shopB.buyGoods(id)
            else -> {}
        }
    }
}

fun main(args: Array<String>) {
    val shopApi = ShopApi()
    shopApi.buyGoods("A",1)
    shopApi.buyGoods("B",2)
}