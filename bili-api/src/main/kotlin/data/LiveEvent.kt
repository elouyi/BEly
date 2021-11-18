package com.elouyi.bely.data


public sealed interface LiveEvent {

    /**
     * 原始数据
     */
    public val data: ByteArray

    /**
     * 心跳包回复
     * @property popularity 人气值
     */
    public class HeartBeat internal constructor(
        public val popularity: Int,
        override val data: ByteArray
    ) : LiveEvent

    public class Danmu internal constructor(
        public val content: String,
        override val data: ByteArray
    ) : LiveEvent

    /**
     * 未知
     */
    public class Unknown internal constructor(
        public val unknownData: ByteArray
    ) : LiveEvent {

        @Deprecated(
            "data is unknown,use unknownData instead",
            ReplaceWith("unknownData"),
            level = DeprecationLevel.ERROR
        )
        override val data: ByteArray
            get() = unknownData
    }

}
