package com.cody.haievents.Show.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable





@Serializable
data class GaneshTheaterGetSeatResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("seatConfig") val seatConfig: List<SeatRowConfig>
)

@Serializable
data class SeatRowConfig(
    @SerialName("row") val row: String,
    @SerialName("type") val type: String,
    @SerialName("color") val color: String,
    @SerialName("price") val price: Int,
    @SerialName("blocks") val blocks: List<List<Int>>,
    @SerialName("reservedSeats") val reservedSeats: List<Int>
)




@Serializable
data class  GaneshTheaterBookingRequest(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: String,
    @SerialName("item_id") val itemId: Int,
    @SerialName("seats") val seats: List<Seat>
)

@Serializable
data class Seat(
    @SerialName("row") val row: String,
    @SerialName("number") val number: Int
)

@Serializable
data class GaneshTheaterBookingResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String
)

