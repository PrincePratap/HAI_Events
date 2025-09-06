package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.Show.model.BlogsListResponse
import com.cody.haievents.Show.model.CategoryResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class BlogsListUseCase : KoinComponent {
    private val repository: ShowRepository by inject()
    suspend operator fun invoke(
    ): Result<BlogsListResponse> {


        return repository.getBlogsList()
    }
}