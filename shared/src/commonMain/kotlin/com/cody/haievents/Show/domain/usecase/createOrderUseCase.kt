package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.model.OrderResponse
import com.cody.haievents.Show.model.ShowDetailPageResponse
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject





class createOrderUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        totalAmount : String,

        ): Result<OrderResponse> {


        return repository.createOrder(totalAmount)
    }
}