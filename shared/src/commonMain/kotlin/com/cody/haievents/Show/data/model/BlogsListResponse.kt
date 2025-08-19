package com.cody.haievents.Show.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogsListResponse(
    val status: String,
    val blogs: List<Blog>,
)
@Serializable
data class Blog(
    val id: Long,
    val title: String,
    val slug: String,
    val content: String,
    val image: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
)
