package com.cody.haievents.blog.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogDetailResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("blog") val blog: Blog
)

@Serializable
data class Blog(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("slug") val slug: String,
    @SerialName("content") val content: String,
    @SerialName("image") val image: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)
