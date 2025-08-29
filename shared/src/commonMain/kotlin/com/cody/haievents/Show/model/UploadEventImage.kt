package com.cody.haievents.Show.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class UploadEventImage(
    @SerialName("message") val message: String,
    @SerialName("image_path") val imagePath: String,
    @SerialName("image_url") val imageUrl: String
)