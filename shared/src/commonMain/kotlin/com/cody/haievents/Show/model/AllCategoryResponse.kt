package com.cody.haievents.Show.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllCategoryResponse(
    val status: Boolean,
    val message: String,
    val data: List<CategoryItem>
)

@Serializable
data class CategoryItem(
    val id: Int,
    val name: String,
    @SerialName("image_path")
    val imagePath: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)
