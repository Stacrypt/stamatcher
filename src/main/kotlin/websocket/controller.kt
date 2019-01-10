package websocket

import io.ktor.http.cio.websocket.WebSocketSession

enum class SubscriptionTopic {
    MARKET, TICK, DEAL, BOOK
}

class WebsocketController {
    suspend fun subscribe(socket: WebSocketSession) {

    }

    suspend fun unsubscribe(socket: WebSocketSession) {

    }

    suspend fun ping(socket: WebSocketSession) {

    }

    suspend fun time(socket: WebSocketSession) {

    }
}