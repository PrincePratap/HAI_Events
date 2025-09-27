package com.cody.haievents.di

import com.cody.haievents.Show.data.ShowRepositoryImpl
import com.cody.haievents.Show.data.ShowService
import com.cody.haievents.Show.model.UploadEventImage
import com.cody.haievents.Show.domain.repository.ShowRepository
import com.cody.haievents.Show.domain.usecase.AllCategoriesUseCase
import com.cody.haievents.Show.domain.usecase.BlogsItemUseCase
import com.cody.haievents.Show.domain.usecase.BlogsListUseCase
import com.cody.haievents.Show.domain.usecase.CategoryItemsUseCase
import com.cody.haievents.Show.domain.usecase.EventDetailsUseCase
import com.cody.haievents.Show.domain.usecase.EventImageAndTicketsUseCase
import com.cody.haievents.Show.domain.usecase.GaneshTheaterUseCase
import com.cody.haievents.Show.domain.usecase.MyTicketDetailsUseCase
import com.cody.haievents.Show.domain.usecase.MyTicketListUseCase
import com.cody.haievents.Show.domain.usecase.SearchTextUseCase
import com.cody.haievents.Show.domain.usecase.ShowDetailUseCase
import com.cody.haievents.Show.domain.usecase.TicketListUseCase
import com.cody.haievents.Show.domain.usecase.UploadImageUseCase
import com.cody.haievents.Show.domain.usecase.createOrderUseCase
import com.cody.haievents.auth.data.AuthRepositoryImpl
import com.cody.haievents.auth.data.AuthService
import com.cody.haievents.auth.domain.repository.AuthRepository
import com.cody.haievents.auth.domain.usecase.ForgetPasswordOtpUseCase
import com.cody.haievents.auth.domain.usecase.ForgetPasswordReSendOTPUseCase
import com.cody.haievents.auth.domain.usecase.ForgetPasswordUseCase
import com.cody.haievents.auth.domain.usecase.GetUserUseCase
import com.cody.haievents.auth.domain.usecase.HomePageUseCase
import com.cody.haievents.auth.domain.usecase.LogInUseCase
import com.cody.haievents.auth.domain.usecase.NewPasswordUseCase
import com.cody.haievents.auth.domain.usecase.OtpVerificationUseCase
import com.cody.haievents.auth.domain.usecase.ReSendOTPUseCase
import com.cody.haievents.auth.domain.usecase.RegisterUseCase
import com.cody.haievents.auth.domain.usecase.ResetPasswordUseCase
import com.cody.haievents.auth.domain.usecase.TermsConditionsUseCase
import com.cody.haievents.auth.domain.usecase.UpdateUserUseCase
import com.cody.haievents.common.util.provideDispatcher
import com.cody.haievents.phonepe.data.PhonePeRepositoryImpl
import com.cody.haievents.phonepe.data.PhonePeService
import com.cody.haievents.phonepe.domain.repository.PhonePeRepository
import com.cody.haievents.phonepe.domain.usecase.BuyTicketUseCase
import com.cody.haievents.phonepe.domain.usecase.PhonePeGaneshTheatreUseCase
import com.cody.haievents.phonepe.domain.usecase.TicketPurchaseUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(),get()) }
    factory { AuthService() }
    factory { LogInUseCase() }
    factory { NewPasswordUseCase() }
    factory { OtpVerificationUseCase() }
    factory { RegisterUseCase() }
    factory { OtpVerificationUseCase() }
    factory { HomePageUseCase() }
    factory { GetUserUseCase() }
    factory { EventDetailsUseCase() }
    factory { UpdateUserUseCase() }
    factory { TermsConditionsUseCase() }
    factory { ForgetPasswordUseCase() }
    factory { ForgetPasswordOtpUseCase() }
    factory { ResetPasswordUseCase() }
    factory { UploadImageUseCase() }
    factory { EventImageAndTicketsUseCase() }
    factory { ForgetPasswordReSendOTPUseCase() }
    factory { ReSendOTPUseCase() }
}


private val PhonePeModule = module {
    single<PhonePeRepository> { PhonePeRepositoryImpl(get(), get(),get()) }
    factory { PhonePeService() }
    factory { PhonePeGaneshTheatreUseCase() }
    factory { TicketPurchaseUseCase() }
}



private val ShowModule = module {
    single<ShowRepository> { ShowRepositoryImpl(get(), get(),get()) }
    factory { ShowService() }
    factory { ShowDetailUseCase() }
    factory { TicketListUseCase() }
    factory { SearchTextUseCase() }
    factory { createOrderUseCase() }
    factory { UploadImageUseCase() }
    factory { EventDetailsUseCase() }
    factory { GaneshTheaterUseCase() }
    factory { AllCategoriesUseCase() }
    factory { CategoryItemsUseCase() }
    factory { BlogsListUseCase() }
    factory { BlogsItemUseCase() }
    factory { BuyTicketUseCase() }
    factory { MyTicketListUseCase() }
    factory { MyTicketDetailsUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

fun getSharedModules() = listOf(
    ShowModule,
    platformModule,
    authModule,
    utilityModule,
    PhonePeModule
)