package ch11

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedAbstractActor
import akka.pattern.Patterns
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.*

class ShopActor(val stocks: HashMap<Long, Int>) : UntypedAbstractActor() {
    var orderNumber = 1L
    override fun onReceive(message: Any?) {
        when (message) {
            is Action.Buy -> {
                val stock = stocks.getValue(message.id)
                if (stock > message.amount) {
                    stocks.plus(Pair(message.id, stock - message.amount))
                    sender.tell(orderNumber, self)
                    orderNumber++
                } else {
                    sender.tell("low stocks", self)
                }
            }
            is Action.GetStock -> {
                sender.tell(stocks.get(message.id), self)
            }
        }
    }
}

sealed class Action {
    data class BuyOrInit(val id: Long, val userId: Long, val amount: Long, val shopName: String, val stocks: Map<Long, Int>) : Action()
    data class Buy(val id: Long, val userId: Long, val amount: Long) : Action()
    data class GetStock(val id: Long) : Action()
    data class GetStockOrInit(val id: Long, val shopName: String, val stocks: Map<Long, Int>) : Action()
}

class ManageActor : UntypedAbstractActor() {
    override fun onReceive(message: Any?) {
        when (message) {
            is Action.BuyOrInit -> getOrInit(message.shopName,message.stocks).forward(Action.Buy(message.id, message.userId, message.amount), context)
            is Action.GetStockOrInit -> getOrInit(message.shopName,message.stocks).forward(Action.GetStock(message.id), context)
        }
    }

    fun getOrInit(shopName: String, stocks: Map<Long, Int>): ActorRef {
        return context.findChild("shop-actor-${shopName}").orElseGet { context.actorOf(Props.create(ShopActor::class.java, stocks), "shop-actor-${shopName}") }
    }

}

fun main(args: Array<String>) {
    val stocksA = hashMapOf(Pair(1L, 10), Pair(2L, 5), Pair(3L, 20))
    val stocksB = hashMapOf(Pair(1L, 15), Pair(2L, 8), Pair(3L, 30))
    val actorSystem = ActorSystem.apply("shop-system") //
    val manageActor = actorSystem.actorOf(Props.create(ManageActor::class.java), "manage-actor")
    val timeout = Timeout(Duration.create(3, "seconds"))

    val resA = Patterns.ask(manageActor, Action.GetStockOrInit(1L, "A", stocksA), timeout)
    val stock = Await.result(resA, timeout.duration())
    println("the stock is ${stock}")

    val resB = Patterns.ask(manageActor, Action.BuyOrInit(2L, 1L, 1,"B", stocksB), timeout)
    val orderNumber = Await.result(resB, timeout.duration())
    println("the orderNumber is ${orderNumber}")


}