package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.Show.model.BlogItemResponse
import com.cody.haievents.Show.model.CategoryItemsResponse
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class BlogsItemUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        blogId : Int,

        ): Result<BlogItemResponse> {


        return repository.getBlogsItem(blogId)
    }
}