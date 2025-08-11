package com.cody.haievents.Show.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



/**
 * The root-level response object containing lists of movies and events.
 */
@Serializable
data class SearchShowResponse(
    @SerialName("movies")
    val movies: List<Movie>,

    @SerialName("events")
    val events: List<Event>
)

/**
 * Represents a single movie item from the "movies" list.
 */
@Serializable
data class Movie(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("slug")
    val slug: String,

    @SerialName("language")
    val language: String,

    @SerialName("image_path")
    val imagePath: String,

    @SerialName("rating")
    val rating: Int,

    // Defined as String? to handle both integer and large string values safely.
    @SerialName("votes")
    val votes: String?,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("category_id")
    val categoryId: Int,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("detail")
    val detail: MovieDetail
)

/**
 * Represents the nested "detail" object inside a Movie.
 */
@Serializable
data class MovieDetail(
    @SerialName("id")
    val id: Int,

    @SerialName("movie_id")
    val movieId: Int,

    @SerialName("time_range")
    val timeRange: String,

    @SerialName("summary")
    val summary: String,

    @SerialName("venue")
    val venue: String
)

/**
 * Represents a single event item from the "events" list.
 */
@Serializable
data class Event(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("slug")
    val slug: String,

    @SerialName("description")
    val description: String,

    @SerialName("category_id")
    val categoryId: Int,

    @SerialName("user_id")
    val userId: Int,

    @SerialName("image_path")
    val imagePath: String,

    @SerialName("organizer_name")
    val organizerName: String,

    @SerialName("email")
    val email: String,

    @SerialName("location")
    val location: String,

    @SerialName("date")
    val date: String,

    @SerialName("time")
    val time: String,

    @SerialName("account_holder")
    val accountHolder: String?,

    @SerialName("bank_name")
    val bankName: String?,

    @SerialName("ifsc_code")
    val ifscCode: String?,

    @SerialName("account_number")
    val accountNumber: String?,

    @SerialName("bank_phone_number")
    val bankPhoneNumber: String?,

    @SerialName("price")
    val price: String?,

    @SerialName("performer_price")
    val performerPrice: String?,

    @SerialName("attendee_price")
    val attendeePrice: String?,

    @SerialName("is_approved")
    val isApproved: Int,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)