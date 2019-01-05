import java.lang.Exception
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class MarketOrderKilledException(order: Order) : Exception()

class Book {

    val buyQueue = PriorityQueue<Order>(betterBuyOrderComparator)
    val sellQueue = PriorityQueue<Order>(betterSellOrderComparator)

    private fun chooseRightQueue(order: Order) = if (order.side == Side.BUY) buyQueue else sellQueue

    private fun meetOrders(makerOrder: Order, takerOrder: Order, amount: Long): Pair<Fill, Fill> {
        return Pair(
            Fill(makerOrder, amount, makerOrder.limitPrice!!, Role.MAKER),
            Fill(takerOrder, amount, makerOrder.limitPrice, Role.TAKER)
        )
    }


    @Synchronized
    fun processOrder(order: Order) = when (order.orderType) {
        OrderType.MARKET -> processMarketOrder(order)
        else -> throw Exception("Not supported yet!")
    }

    private fun processMarketOrder(order: Order): List<Fill> {
        if (order.orderType != OrderType.MARKET) {
            throw Exception("Not supported yet!")
        }

        val queue = chooseRightQueue(order)
        val totalOrderAmount = order.amount
        var partialMatchedLiquidity: Pair<Order, Long>? = null
        val matchedLiquidityList = ArrayList<Order>()
        val fills = ArrayList<Fill>()

        for (liquidOrder in queue) {
            if (liquidOrder.amount >= order.amount) {

                fills.addAll(meetOrders(liquidOrder, order, order.amount).toList())
                if (liquidOrder.amount == order.amount) matchedLiquidityList.add(liquidOrder)
                else partialMatchedLiquidity = Pair(order, order.amount)
                order.amount = 0
                break

            } else {

                fills.addAll(meetOrders(liquidOrder, order, liquidOrder.amount).toList())
                matchedLiquidityList.add(liquidOrder)
                order.amount -= liquidOrder.amount

            }
        }

        if (order.amount > 0 && order.fillOrKill) {
            // Market order has not been filled completely and the queue is empty.
            order.amount = totalOrderAmount
            throw MarketOrderKilledException(order)
        } else {
            // Everything is ok, so commit
            queue.removeAll(matchedLiquidityList)
            partialMatchedLiquidity?.apply { first.amount -= second }
        }

        return fills
    }
}

object betterBuyOrderComparator : BetterOrderComparator(Side.BUY)
object betterSellOrderComparator : BetterOrderComparator(Side.SELL)

open class BetterOrderComparator(private val side: Side) : Comparator<Order> {
    override fun compare(o1: Order?, o2: Order?): Int {
        if (o1!!.limitPrice!! == o2!!.limitPrice!!) return (if (o1.timestamp == o2.timestamp) 0 else if (o1.timestamp < o2.timestamp) 1 else -1)
        return (if (o1.limitPrice!! > o2.limitPrice!!) 1 else -1) * (if (side == Side.BUY) 1 else -1)
    }
}