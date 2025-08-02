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

// Home Page
@Serializable
data class Root(
    val status: Boolean,
    val message: String,
    val categories: List<Category>,
    val featured: List<Featured>,
    val tours: List<Tour>,
    val banners: List<Banner>,
)

@Serializable
data class Category(
    val id: Long,
    val name: String,
    @SerialName("image_path")
    val imagePath: String,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
)

@Serializable
data class Featured(
    val id: Long,
    val name: String,
    @SerialName("image_path")
    val imagePath: String,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    val movies: List<Movie>,
    @SerialName("user_events")
    val userEvents: List<UserEvent>,
)

@Serializable
data class Movie(
    val id: Long,
    val title: String,
    val slug: String,
    val language: String,
    @SerialName("image_path")
    val imagePath: String,
    val rating: Long,
    val votes: Long? = null,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("category_id")
    val categoryId: Long,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
)

@Serializable
data class UserEvent(
    val id: Long,
    val title: String,
    val slug: String,
    val description: String,
    @SerialName("category_id")
    val categoryId: Long,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("image_path")
    val imagePath: String?,
    @SerialName("organizer_name")
    val organizerName: String,
    val email: String,
    val location: String,
    val date: String,
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
    @SerialName("is_approved")
    val isApproved: Long,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
)

@Serializable
data class Tour(
    val id: Long,
    @SerialName("artist_id")
    val artistId: Long,
    val title: String,
    val image: String,
    val description: String,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("end_date")
    val endDate: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
)

@Serializable
data class Banner(
    val id: Long,
    val type: String,
    @SerialName("file_path")
    val filePath: String,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
)

