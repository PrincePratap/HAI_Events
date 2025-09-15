package com.cody.haievents.android.screens.addEvent.bankTransferDetails

import androidx.compose.runtime.Composable
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun BankTransferDetails(
    navigator: DestinationsNavigator,
    payload: CreateUserEventRequest = CreateUserEventRequest()
) {

    BankTransferDetailsScreen()
}