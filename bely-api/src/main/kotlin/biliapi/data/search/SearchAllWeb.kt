package com.elouyi.bely.biliapi.data.search

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

data class SearchAllWebResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: SearchAllWebData
) : BiliResponse<SearchAllWebData>

@Serializable
data class SearchAllWebData(
    val seid: String,
    val page: Int,
    val pagesize: Int,
    val numResults: Int,
    val numPages: Int,
    val suggest_keyword: String?,
    val rqt_type: String,
    val cost_time: CostTime,
    @Contextual
    val exp_list: Nothing?,
    val egg_hit: Int,
    val pageinfo: PageInfo,
    val top_tlist: TopTList,
    val show_column: Int,
    val show_module_list: List<String>,
    @Contextual
    val result: Any
)

@Serializable
data class CostTime(
    val params_check: String,
    val illegal_handler: String,
    val as_response_format: String,
    val as_request: String,
    val save_cache: String,
    val deserialize_response: String,
    val as_request_format: String,
    val total: String,
    val main_handler: String
)

@Serializable
/**
 * @property numResult 总计数量
 * @property total 总计数量
 * @property pages 分页数量
 */
data class PageInfoObj(
    val numResult: Int,
    val total: Int,
    val pages: Int,
)

@Serializable
data class PageInfo(
    val pgc: PageInfoObj,
    val live_room: PageInfoObj,
    val photo: PageInfoObj,
    val topic: PageInfoObj,
    val video: PageInfoObj,
    val user: PageInfoObj,
    val bili_user: PageInfoObj,
    val media_ft: PageInfoObj,
    val article: PageInfoObj,
    val media_bangumi: PageInfoObj,
    val special: PageInfoObj,
    val operation_card: PageInfoObj,
    val upuser: PageInfoObj,
    val movie: PageInfoObj,
    val live_all: PageInfoObj,
    val tv: PageInfoObj,
    val live: PageInfoObj,
    val bangumi: PageInfoObj,
    val activity: PageInfoObj,
    val live_master: PageInfoObj,
    val live_user: PageInfoObj
)

@Serializable
data class TopTList(
    val pgc: Int,
    val live_room: Int,
    val photo: Int,
    val topic: Int,
    val video: Int,
    val user: Int,
    val bili_user: Int,
    val media_ft: Int,
    val article: Int,
    val media_bangumi: Int,
    val special: Int,
    val operation_card: Int,
    val upuser: Int,
    val movie: Int,
    val live_all: Int,
    val tv: Int,
    val live: Int,
    val bangumi: Int,
    val activity: Int,
    val live_master: Int,
    val live_user: Int
)
