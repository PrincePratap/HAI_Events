package com.cody.haievents.Show.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class SearchShowResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("results") val results: List<SearchResult>
)

@Serializable
data class SearchResult(
    @SerialName("type") val type: String,
    @SerialName("data") val data: EventData
)

@Serializable
data class EventData(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("date") val date: String,
    @SerialName("time") val time: String,
    @SerialName("image_path") val image: String,
    @SerialName("summary") val summary: String,
    @SerialName("venue") val venue: String
)


