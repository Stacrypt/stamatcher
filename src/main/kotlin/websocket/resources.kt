package websocket

data class DepthBook(
    val pair: String,
    val level: Int,
    val aggregation: Long?,
    val spread: Long,
    val buyOrders: List<DepthBookOrder>,
    val sellOrders: List<DepthBookOrder>
)

data class DepthBookOrder(
    val price: Long,
    val amount: Long
)

data class Tick(
    val openTime: Long,
    val closeTime: Long,
    val open: Long,
    val high: Long,
    val low: Long,
    val close: Long,
    val volume: Long
)

data class Deal(
    val pair: String,
    val price: Long,
    val amount: Long
)