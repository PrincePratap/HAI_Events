package com.cody.haievents.android.common.di

import com.cody.haievents.android.screens.ShowDetailed.ShowDetailedViewModel
import com.cody.haievents.android.screens.auth.login.LoginViewModel
import com.cody.haievents.android.screens.auth.otp.OTPViewModel
import com.cody.haievents.android.screens.auth.register.RegisterViewModel
import com.cody.haievents.android.screens.homePage.HomePageViewModel
import com.cody.haievents.android.screens.search.SearchViewModel
import com.cody.haievents.android.screens.splash.SplashScreenViewModel
import com.cody.haievents.android.screens.ticket.TicketViewModel
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

}