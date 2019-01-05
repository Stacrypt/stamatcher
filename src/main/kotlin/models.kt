import java.util.*

enum class Side { BUY, SELL }
enum class Role { TAKER, MAKER }
enum class OrderType { MARKET, LIMIT, STOP, STOP_LIMIT }

data class Order(
    val timestamp: Long,
    val symbol: String,
    val uid: String,
    val side: Side,
    var amount: Long,
    val limitPrice: Long?,
    val stopPrice: Currency?,
    val fillOrKill: Boolean = false
) {

    val isLimit: Boolean
        get() = limitPrice != null

    val isStop: Boolean
        get() = stopPrice != null

    val orderType: OrderType
        get () = if (isLimit && isStop) OrderType.STOP_LIMIT
        else if (!isLimit && isStop) OrderType.STOP
        else if (isLimit && !isStop) OrderType.LIMIT
        else OrderType.MARKET


}

data class Fill(
    val order: Order,
    val amount: Long,
    val price: Long,
    val role: Role
)

data class DoAdd(val symbol: String, val order: Order)
data class DoCancel(val symbol: String, val orderUid: String)
data class DoChange(val symbol: String, val orderUid: String)
