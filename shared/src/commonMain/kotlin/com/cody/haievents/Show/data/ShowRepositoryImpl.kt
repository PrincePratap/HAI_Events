package com.cody.haievents.Show.data

import com.cody.haievents.Show.data.model.AddEventRequest
import com.cody.haievents.Show.data.model.AddEventResponse
import com.cody.haievents.Show.data.model.BlogsListResponse
import com.cody.haievents.Show.data.model.CategoryResponse
import com.cody.haievents.Show.data.model.OrderRequest
import com.cody.haievents.Show.data.model.OrderResponse
import com.cody.haievents.Show.data.model.SearchShowResponse
import com.cody.haievents.Show.data.model.ShowDetailPageResponse
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.auth.data.AuthService
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.data.local.UserPreferences
import com.cody.haievents.common.util.DispatcherProvider
import kotlinx.coroutines.withContext
import com.cody.haievents.common.util.Result


internal class ShowRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val showService: ShowService,
    private val userPreferences: UserPreferences
) : ShowRepository {
    override suspend fun showDetails(showId: Int): Result<ShowDetailPageResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.getShowDetail(showId, userPreferences.getUserData().token)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun ticketPrice(showId: Int): Result<getShowTicketResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.getTicketPrice(showId)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun searchShow(search: String): Result<SearchShowResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.searchShow(search)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun getBlogsList(): Result<BlogsListResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.getBlogsList()
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }

    override suspend fun createOrder(totalAmount: String): Result<OrderResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.createOrder(OrderRequest(amount = totalAmount.toInt()))
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to Create Order: ${e.message}")
            }
        }
    }

    override suspend fun addEvent(requestBody: AddEventRequest): Result<AddEventResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.addEvent(requestBody)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to Create Order: ${e.message}")
            }
        }
    }

    override suspend fun getAllCategories(): Result<CategoryResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.getALLCategories()
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to Create Order: ${e.message}")
            }
        }
    }


}