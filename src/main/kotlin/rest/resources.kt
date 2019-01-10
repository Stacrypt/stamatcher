package rest

data class Asset(val userId: String, val symbol: String, val total: Long, val freeze: Long)

data class Market(val pair: String, val status24h: MarketStatus)
data class MarketStatus(
    val openTime: Long,
    val closeTime: Long,
    val open: Long,
    val high: Long,
    val low: Long,
    val close: Long,
    val volume: Long

)

enum class OrderSide { BUY, SELL }
enum class OrderType { MARKET, LIMIT, STOP, STOP_LIMIT }
enum class OrderStatus {
    LIQUID, // Open order (Active)
    FULLFILLED, // Fully filled (Inactive)
    PARTFILLED, // Partially filled (Inactive)
    WITING, // When stop or stop-limit waiting for trigger (Inactive)
    CANCELED, // Manually canceled (Inactive)
    KILLED // When killOrFill option is enable and couldn't fully filled (Inactive)
}

data class Order(
    val createdAt: Long,
    val userId: String,
    val orderId: String,
    val pair: String,
    val totalAmount: Long,
    val filledAmount: Long = 0L,
    val limitPrice: Long?,
    val stopPrice: Long?,
    val side: OrderSide,
    val type: OrderType,
    val status: OrderStatus,
    val killOrFill: Boolean = false
)

enum class FillRole { MAKER, TAKER }
data class Fill(
    val createdAt: Long,
    val orderId: String,
    val pair: String,
    val price: Long,
    val amount: Long,
    val fee: Long,
    val role: FillRole
)

