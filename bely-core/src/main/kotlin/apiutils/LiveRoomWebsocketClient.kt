package com.elouyi.bely.apiutils

import com.elouyi.bely.biliapi.data.live.HeartBeatResponse
import com.elouyi.bely.biliapi.data.live.LiveRoomEvent
import com.elouyi.bely.biliapi.data.live.OnError
import com.elouyi.bely.biliapi.data.live.UnknownEvent
import com.elouyi.bely.utils.LiveRoomEventLogger
import com.elouyi.bely.utils.LogLevel
import com.elouyi.bely.utils.decompress
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI
import java.nio.ByteBuffer

class LiveRoomWebsocketClient(
    val info: LiveRoomSocketInfo
) : WebSocketClient(URI(info.url)),
    Channel<LiveRoomEvent> by Channel(),
    CoroutineScope
{

    override val coroutineContext = CoroutineName("live[${info.shortId}]") + Dispatchers.IO +
            CoroutineExceptionHandler {coroutineContext, throwable ->
                // TODO: 2021/6/22
            }

    private val logger = LiveRoomEventLogger("live[${info.shortId}]")

    override fun onOpen(handshakedata: ServerHandshake?) {
        logger.v("连接成功，开始验证")
        logger.v(String(info.h))
        send(info.h)
        launch {
            while (isActive) {
                delay(3000)
                send(info.heartBeat)
                logger.v("发送心跳包")
            }
        }
    }

    override fun onMessage(message: String?) {
        if (message == null) return
        logger.d(message)
        sende(UnknownEvent(message))
    }

    override fun onMessage(bytes: ByteBuffer?) {
        if (bytes == null) return
        val es = handleMessage(bytes)
        for (e in es) {
            sende(e)
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        logger.i("socket close")
    }

    override fun onError(ex: Exception?) {
        if (ex == null) return
        logger.e(ex)
        sende(OnError(ex))

    }

    private fun sende(e: LiveRoomEvent) {
        launch {
            send(e)
        }
    }

    fun setLoggerLevel(level: LogLevel) {
        logger.level = level
    }

    // todo
    private fun handleMessage(bytes: ByteBuffer): List<LiveRoomEvent> {
        val list = mutableListOf<LiveRoomEvent>()
        val type = bytes.getInt(8)
        val len = bytes.getInt(0) - 16
        val data = ByteArray(len) {
            bytes[it + 16]
        }
        when(type) {
            3 -> {
                val p = bytes.getInt(16)
                logger.v("收到心跳回复，直播间人气为 $p")
                list.add(HeartBeatResponse(p))
            }
            5 -> {
                when(bytes.getShort(6).toInt()) {
                    2 -> {
                        val c = decompress(data)
                        val cr = ByteArray(c.size - 16) {
                            c[it + 16]
                        }
                        list.add(UnknownEvent(String(cr)))
                        logger.v(String(cr))
                    }
                    0 -> {
                        list.add(UnknownEvent(String(data)))
                        logger.v(String(data))
                    }

                }
            }
        }
        return list
    }
}