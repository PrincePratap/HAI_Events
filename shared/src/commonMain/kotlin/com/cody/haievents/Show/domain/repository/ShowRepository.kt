package com.cody.haievents.Show.domain.repository

import com.cody.haievents.Show.data.getShowTicketResponse
import com.cody.haievents.Show.model.AllCategoryResponse
import com.cody.haievents.Show.model.BlogItemResponse
import com.cody.haievents.Show.model.BlogsListResponse
import com.cody.haievents.Show.model.CategoryItemsResponse
import com.cody.haievents.Show.model.CategoryResponse
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.CreateUserEventResponse
import com.cody.haievents.Show.model.GaneshTheaterBookingRequest
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.Show.model.OrderResponse
import com.cody.haievents.Show.model.SearchShowResponse
import com.cody.haievents.Show.model.ShowDetailPageResponse
import com.cody.haievents.Show.model.UploadEventImage

import com.cody.haievents.common.util.Result



internal interface ShowRepository {
    suspend fun showDetails(showId: Int): Result<ShowDetailPageResponse>
    suspend fun ticketPrice(showId: Int): Result<getShowTicketResponse>
    suspend fun searchShow(search: String): Result<SearchShowResponse>
    suspend fun getBlogsList(): Result<BlogsListResponse>
    suspend fun createOrder(totalAmount: String): Result<OrderResponse>
    suspend fun addEvent(requestBody: CreateUserEventRequest): Result<CreateUserEventResponse>
    suspend fun getAllCategories(): Result<CategoryResponse>
    suspend fun uploadEventImage(imageBytes: ByteArray, fileName: String) : Result<UploadEventImage>
    suspend fun getGaneshTheater(): Result<GaneshTheaterGetSeatResponse>
    suspend fun ganeshTheaterBooking(request: GaneshTheaterBookingRequest): Result<GaneshTheaterGetSeatResponse>
    suspend fun getAllCategory(): Result<AllCategoryResponse>
    suspend fun getCategoryItems(categoryID : Int): Result<CategoryItemsResponse>
    suspend fun getBlogsItem(blogsId : Int): Result<BlogItemResponse>



}

