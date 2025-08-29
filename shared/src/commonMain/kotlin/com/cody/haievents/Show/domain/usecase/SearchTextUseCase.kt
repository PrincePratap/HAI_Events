package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.model.SearchShowResponse
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class SearchTextUseCase : KoinComponent {
    private val repository: ShowRepository by inject()

    suspend operator fun invoke(
        search : String,

        ): Result<SearchShowResponse> {




        return repository.searchShow(search)
    }
}