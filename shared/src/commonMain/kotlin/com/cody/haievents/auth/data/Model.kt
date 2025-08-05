package com.cody.haievents.auth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable






// Login Request
@Serializable
data class LoginRequest(
    @SerialName("login")
    val login: String,

    @SerialName("password")
    val password: String
)
@Serializable
data class LoginResponse(
    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String,

    @SerialName("user")
    val user: LoggedInUser
)

@Serializable
data class LoggedInUser(
    @SerialName("id")
    val id: Int,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("email")
    val email: String,

    @SerialName("telephone")
    val telephone: String
)


// register request
@Serializable
data class RegisterRequest(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("telephone") val telephone: String,
    @SerialName("password") val password: String,
    @SerialName("password_confirmation") val passwordConfirmation: String
)

@Serializable
data class RegisterResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String
)

// OTP  request

@Serializable
data class OtpVerificationRequest(
    @SerialName("otp")
    val otp: String,

    @SerialName("token")
    val token: String
)

// Otp Response
@Serializable
data class OTPSuccessResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String,

    @SerialName("user")
    val user: RegisteredUser
)

@Serializable
data class RegisteredUser(
    @SerialName("id")
    val id: Int,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("email")
    val email: String,

    @SerialName("telephone")
    val telephone: String
)
// change Password
@Serializable
data class ChangePasswordRequest(
    @SerialName("current_password")
    val currentPassword: String,

    @SerialName("new_password")
    val newPassword: String
)


@Serializable
data class ChangePasswordResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("message")
    val message: String
)

/**
 * Root response object for the homepage API.
 */
@Serializable
data class HomepageResponse(
    @SerialName("status")
    val status: Boolean,

    @SerialName("message")
    val message: String,

    @SerialName("categories")
    val categories: List<Category>,

    @SerialName("featured")
    val featured: List<FeaturedItem>,

    @SerialName("tours")
    val tours: List<Tour>,

    @SerialName("banners")
    val banners: List<Banner>
)

@Serializable
data class Category(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("image_path")
    val imagePath: String,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null
)

@Serializable
data class FeaturedItem(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("image_path")
    val imagePath: String,

    @SerialName("movies")
    val movies: List<Movie>,

    @SerialName("user_events")
    val userEvents: List<UserEvent>,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null
)

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

    // Note: 'votes' is sometimes a number and sometimes a very long string.
    // Parsing as a String is the safest approach to avoid deserialization errors.
    @SerialName("votes")
    val votes: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("category_id")
    val categoryId: Int,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null
)

@Serializable
data class UserEvent(
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
    val imagePath: String?,

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
    val accountHolder: String,

    @SerialName("bank_name")
    val bankName: String,

    @SerialName("ifsc_code")
    val ifscCode: String,

    @SerialName("account_number")
    val accountNumber: String,

    @SerialName("bank_phone_number")
    val bankPhoneNumber: String,

    // Price fields are nullable in the JSON
    @SerialName("price")
    val price: String? = null,

    @SerialName("performer_price")
    val performerPrice: String? = null,

    @SerialName("attendee_price")
    val attendeePrice: String? = null,

    // is_approved is 0 or 1, which maps well to Int
    @SerialName("is_approved")
    val isApproved: Int,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class Tour(
    @SerialName("id")
    val id: Int,

    @SerialName("artist_id")
    val artistId: Int,

    @SerialName("title")
    val title: String,

    @SerialName("image")
    val image: String,

    @SerialName("description")
    val description: String,

    @SerialName("start_date")
    val startDate: String,

    @SerialName("end_date")
    val endDate: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class Banner(
    @SerialName("id")
    val id: Int,

    @SerialName("type")
    val type: String,

    @SerialName("file_path")
    val filePath: String,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("updated_at")
    val updatedAt: String? = null
)

