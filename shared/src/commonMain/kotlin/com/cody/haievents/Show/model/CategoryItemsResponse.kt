package com.cody.haievents.Show.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemsResponse(
    val status: Boolean,
    @SerialName("category_id") val categoryId: String,
    val items: List<CategoryItemWrapper>
)

@Serializable
data class CategoryItemWrapper(
    val type: String,
    val data: EventDatas
)

@Serializable
data class EventDatas(
    val id: Int,
    val title: String,
    val slug: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("category_id") val categoryId: Int,
    val date: String,
    val time: String,
    val summary: String,
    val venue: String
)
