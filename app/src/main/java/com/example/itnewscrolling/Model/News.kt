package com.example.itnewscrolling.Model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsList(
    val status : String?,
    val sources : List<News>
)

@JsonClass(generateAdapter = true)
data class News(
    @Json(name = "id")
    val id : String?,
    @Json(name = "name")
    val name : String?,
    @Json(name = "description")
    val description : String?,
    @Json(name = "url")
    val url : String?,
    @Json(name = "category")
    val category : String?

)