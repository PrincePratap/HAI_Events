package com.cody.haievents.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class HomePageResponse(
    @SerialName("status") val status: Boolean? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("categories") val categories: List<Category>? = null,
    @SerialName("featured") val featured: List<FeaturedCategory>? = null,
    @SerialName("tours") val tours: List<Tour>? = null,
    @SerialName("banners") val banners: List<Banner>? = null
)

@Serializable
data class Category(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("image_path") val imagePath: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class FeaturedCategory(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("image_path") val imagePath: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("items") val items: List<FeaturedItem>
)

@Serializable
data class FeaturedItem(
    @SerialName("type") val type: String? = null,
    @SerialName("data") val data: ItemData? = null
)

@Serializable
data class ItemData(
    @SerialName("id") val id: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("image_path") val imagePath: String? = null,
    @SerialName("category_id") val categoryId: Int? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("time") val time: String? = null,
    @SerialName("summary") val summary: String? = null,
    @SerialName("venue") val venue: String? = null
)

@Serializable
data class Tour(
    @SerialName("id") val id: Int? = null,
    @SerialName("artist_id") val artistId: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("start_date") val startDate: String? = null,
    @SerialName("end_date") val endDate: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class Banner(
    @SerialName("id") val id: Int? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("file_path") val filePath: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)
