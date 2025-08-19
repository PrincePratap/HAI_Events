package com.cody.haievents.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomePageResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("categories") val categories: List<Category>,
    @SerialName("featured") val featured: List<FeaturedCategory>,
    @SerialName("tours") val tours: List<Tour>,
    @SerialName("banners") val banners: List<Banner>
)

@Serializable
data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class FeaturedCategory(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("items") val items: List<FeaturedItem>
)

@Serializable
data class FeaturedItem(
    @SerialName("type") val type: String,
    @SerialName("data") val data: ItemData
)

@Serializable
data class ItemData(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("slug") val slug: String,
    @SerialName("image_path") val imagePath: String? = null,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("date") val date: String,
    @SerialName("time") val time: String,
    @SerialName("summary") val summary: String,
    @SerialName("venue") val venue: String
)

@Serializable
data class Tour(
    @SerialName("id") val id: Int,
    @SerialName("artist_id") val artistId: Int,
    @SerialName("title") val title: String,
    @SerialName("image") val image: String,
    @SerialName("description") val description: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class Banner(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("file_path") val filePath: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)
