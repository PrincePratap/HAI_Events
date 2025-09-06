package com.cody.haievents.phonepe.data


import com.cody.haievents.common.data.local.UserPreferences
import com.cody.haievents.common.util.DispatcherProvider
import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.domain.repository.PhonePeRepository
import com.cody.haievents.phonepe.model.PaymentRequestGaneshTheatre
import com.cody.haievents.phonepe.model.PaymentResponseGaneshTheatre
import kotlinx.coroutines.withContext




internal class PhonePeRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val phonePeService: PhonePeService,
    private val userPreferences: UserPreferences
) : PhonePeRepository {



    override suspend fun createGaneshTheatreOrder(request: PaymentRequestGaneshTheatre): Result<PaymentResponseGaneshTheatre> {
        return withContext(dispatcher.io) {
            try {
                val response = phonePeService.createGaneshTheatreOrder(request)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(message = "Failed to send OTP: ${e.message}")
            }
        }
    }


}
