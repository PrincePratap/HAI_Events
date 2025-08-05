package com.cody.haievents.auth.domain.model

import com.cody.haievents.auth.data.Movie


data class HomePageData(
    val title: String,
    val showList : List<Movie>
)


