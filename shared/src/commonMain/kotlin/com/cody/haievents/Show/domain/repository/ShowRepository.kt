package com.cody.haievents.Show.domain.repository

import com.cody.haievents.Show.data.getShowTicketResponse
import com.cody.haievents.Show.data.model.BlogsListResponse
import com.cody.haievents.Show.data.model.CategoryResponse
import com.cody.haievents.Show.data.model.CreateUserEventRequest
import com.cody.haievents.Show.data.model.CreateUserEventResponse
import com.cody.haievents.Show.data.model.OrderResponse
import com.cody.haievents.Show.data.model.SearchShowResponse
import com.cody.haievents.Show.data.model.ShowDetailPageResponse

import com.cody.haievents.common.util.Result



internal interface ShowRepository {
    suspend fun showDetails(showId: Int): Result<ShowDetailPageResponse>
    suspend fun ticketPrice(showId: Int): Result<getShowTicketResponse>
    suspend fun searchShow(search: String): Result<SearchShowResponse>
    suspend fun getBlogsList(): Result<BlogsListResponse>
    suspend fun createOrder(totalAmount: String): Result<OrderResponse>
    suspend fun addEvent(requestBody: CreateUserEventRequest): Result<CreateUserEventResponse>
    suspend fun getAllCategories(): Result<CategoryResponse>
}

