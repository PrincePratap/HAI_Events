package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.data.model.CategoryResponse
import com.cody.haievents.Show.data.model.OrderResponse
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class AllCategoriesUseCase : KoinComponent {
    private val repository: ShowRepository by inject()
    suspend operator fun invoke(
    ): Result<CategoryResponse> {


        return repository.getAllCategories()
    }
}
