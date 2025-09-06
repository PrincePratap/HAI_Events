package com.cody.haievents.Show.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class BlogsListResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("blogs") val blogs: List<Blog>
)

@Serializable
data class Blog(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("slug") val slug: String,
    @SerialName("content") val content: String,
    @SerialName("image") val image: String,
    @SerialName("date") val date: String,
    @SerialName("time") val time: String
)

@Serializable
data class BlogItemResponse(
    val status: Boolean,
    val message: String,
    val blog: Blog
)








