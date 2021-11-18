package com.elouyi.bely.data


public sealed interface LiveEvent {
    /**
     * 心跳包回复
     * @property popularity 人气值
     */
    public class HeartBeat internal constructor(
        public val popularity: Int,
    ) : LiveEvent {
        override fun toString(): String {
            return "心跳回复-人气值:$popularity"
        }
    }

    public class Danmu internal constructor(
        public val content: String,
    ) : LiveEvent {

        override fun toString(): String {
            return "弹幕: $content"
        }
    }

    /**
     * 未知
     */
    public class Unknown internal constructor(
        public val unknownData: ByteArray
    ) : LiveEvent {

        override fun toString(): String {
            return "Unknown live event: $unknownData"
        }
    }

}
