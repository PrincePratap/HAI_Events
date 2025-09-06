package com.cody.haievents.android.common.di

import com.cody.haievents.android.screens.Blogs.BlogsViewModel
import com.cody.haievents.android.screens.GaneshTheater.GaneshTheaterViewModel
import com.cody.haievents.android.screens.ShowDetailed.ShowDetailedViewModel
import com.cody.haievents.android.screens.addEvent.eventDetails.EventDetailsViewModel
import com.cody.haievents.android.screens.allCategories.AllCategoriesViewModel
import com.cody.haievents.android.screens.auth.login.LoginViewModel
import com.cody.haievents.android.screens.auth.otp.OTPViewModel
import com.cody.haievents.android.screens.auth.register.RegisterViewModel
import com.cody.haievents.android.screens.blogsDetail.BlogsDetailViewModel
import com.cody.haievents.android.screens.categorieShows.CategoriesShowsViewModel
import com.cody.haievents.android.screens.editProfile.EditProfileViewModel
import com.cody.haievents.android.screens.homePage.HomePageViewModel
import com.cody.haievents.android.screens.search.SearchViewModel
import com.cody.haievents.android.screens.splash.SplashScreenViewModel
import com.cody.haievents.android.screens.ticket.TicketViewModel
import com.cody.haievents.android.screens.webpage.WebPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashScreenViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel {OTPViewModel(get())}
    viewModel {HomePageViewModel(get())}
    viewModel {ShowDetailedViewModel(get())}
    viewModel { TicketViewModel(get(),get()) }
    viewModel { SearchViewModel(get()) }
    viewModel{ EditProfileViewModel(get(),get()) }
    viewModel { EventDetailsViewModel(get(),get())}

    viewModel{ GaneshTheaterViewModel(get(),get()) }
    viewModel{ AllCategoriesViewModel(get()) }
    viewModel{ CategoriesShowsViewModel(get()) }
    viewModel{ BlogsViewModel(get()) }
    viewModel{ BlogsDetailViewModel(get()) }
    viewModel{ WebPageViewModel(get()) }

}