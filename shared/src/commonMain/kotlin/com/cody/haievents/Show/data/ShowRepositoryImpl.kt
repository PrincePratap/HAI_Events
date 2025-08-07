package com.cody.haievents.Show.data

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
    override suspend fun showDetails(showId: Int): Result<ShowEventResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = showService.getShowDetail(showId, userPreferences.getUserData().token)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }


}