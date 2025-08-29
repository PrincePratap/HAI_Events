package com.cody.haievents.Show.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val status: Boolean,
    val order_id: String,
    val amount: String,
    val key: String
)

@Serializable
data class OrderRequest(
    val amount: Int
)