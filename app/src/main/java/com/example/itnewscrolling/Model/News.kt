package com.example.itnewscrolling.Model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsList(
    val status : String?,
    val totalResults : Long?,
    val articles : List<News>
)

@JsonClass(generateAdapter = true)
data class News(
    @Json(name = "title")
    val title : String?,
    @Json(name = "description")
    val description : String?,
    @Json(name = "url")
    val url : String?,
    @Json(name = "urlToImage")
    val urlToImage : String?,
    @Json(name = "content")
    val content : String?
)