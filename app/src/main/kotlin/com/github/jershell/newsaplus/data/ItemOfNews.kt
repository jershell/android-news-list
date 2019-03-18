package com.github.jershell.newsaplus.data

import kotlinx.serialization.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date



@Serializable
data class ItemOfNews(
    @SerialName("tittle")
    val title: String,

    @SerialName("desc")
    val description: String,

    val date: String,

    val pic: String,

    @Optional
    val link: String = ""
)

@Serializable
data class News(@SerialName("News") val items: List<ItemOfNews>)