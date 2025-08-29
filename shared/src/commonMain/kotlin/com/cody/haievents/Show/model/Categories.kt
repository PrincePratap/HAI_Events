package com.cody.haievents.Show.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: List<CategoryData>
)

@Serializable
data class CategoryData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)
