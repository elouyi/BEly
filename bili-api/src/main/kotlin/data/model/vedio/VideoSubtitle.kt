package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoSubtitle(

    /**
     * 是否允许提交字幕
     */
    val allow_submit: Boolean,

    val list: List<SubtitleList>
) {

    @Serializable
    public data class SubtitleList(

        /**
         * 字幕id
         */
        val id: Int,

        /**
         * 字幕语言
         */
        val lan: String,

        /**
         * 字幕语言名称
         */
        val lan_doc: String,

        /**
         * 是否锁定
         */
        val is_lock: Boolean,

        /**
         * 字幕上传者mid
         */
        val author_mid: Int,

        /**
         * json格式字幕文件url
         */
        val subtitle_url: String,

        /**
         * 字幕上传者信息
         */
        val author: Author
    )

    @Serializable
    public data class Author(
        /**
         * 字幕上传者mid
         */
        val mid: Int,

        /**
         * 字幕上传者昵称
         */
        val name: String,

        /**
         * 字幕上传者性别
         */
        val sex: String,

        /**
         * 字幕上传者头像url
         */
        val face: String,

        /**
         * 字幕上传者签名
         */
        val sign: String,

        /**
         * 作用尚不明确
         */
        val rank: Int,

        /**
         * 作用尚不明确
         */
        val birthday: Int,

        /**
         * 作用尚不明确
         */
        val is_fake_account: Int,

        /**
         * 作用尚不明确
         */
        val is_deleted: Int
    )
}
