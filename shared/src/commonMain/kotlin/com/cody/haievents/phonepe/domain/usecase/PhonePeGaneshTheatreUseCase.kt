package com.cody.haievents.phonepe.domain.usecase

import com.cody.haievents.auth.data.model.HomePageResponse
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.domain.repository.PhonePeRepository
import com.cody.haievents.phonepe.model.Meta
import com.cody.haievents.phonepe.model.PaymentRequestGaneshTheatre
import com.cody.haievents.phonepe.model.PaymentResponseGaneshTheatre
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class PhonePeGaneshTheatreUseCase : KoinComponent {
    private val repository: PhonePeRepository by inject()

    suspend operator fun invoke(
        amount: Int,
        meta: Meta,
    ): Result<PaymentResponseGaneshTheatre> {

        // Basic input validation (optional but helpful)
        if (amount <= 0) {
            return Result.Error(message = "Amount must be > 0")
        }

        val request  = PaymentRequestGaneshTheatre(amount, meta)


        return repository.createGaneshTheatreOrder(request)
    }
}