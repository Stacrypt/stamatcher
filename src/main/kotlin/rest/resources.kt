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
    LIQUID, // Active
    FINISH, // Fully filled
    DISCONTINUED, // Partially filled (Inactive)
    STOPPED,
    CANCELED,
    KILLED
}

data class Order(val orderId: String, val pair: String, val status:)

