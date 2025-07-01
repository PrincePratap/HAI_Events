package com.cody.haievents.android.common.di

import com.cody.haievents.android.screens.splash.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//    viewModel { LoginViewModel(get()) }
//    viewModel { SignUpViewModel() }
//    viewModel { HomeViewModel() }
//    viewModel { LawMainViewModel() }
    viewModel { SplashScreenViewModel() }
//    viewModel { MainActivityViewModel() }
//    viewModel { PostDetailViewModel(get(), get(), get(), get(), get())}
//    viewModel { ProfileViewModel(get(), get(), get(), get()) }
//    viewModel { EditProfileViewModel(get(), get(), get()) }
//    viewModel { FollowsViewModel(get()) }
//    single { ImageBytesReader(androidContext()) }
//    viewModel { CreatePostViewModel(get(), get()) }
}