package com.cody.haievents.Show.domain.repository

import com.cody.haievents.Show.data.ShowEventResponse
import com.cody.haievents.Show.data.getShowTicketResponse
import com.cody.haievents.auth.data.ChangePasswordRequest
import com.cody.haievents.auth.data.ChangePasswordResponse
import com.cody.haievents.auth.data.HomepageResponse
import com.cody.haievents.auth.data.LoginRequest
import com.cody.haievents.auth.data.OtpVerificationRequest
import com.cody.haievents.auth.data.RegisterRequest
import com.cody.haievents.auth.data.RegisterResponse
import com.cody.haievents.auth.domain.model.AuthResultData
import com.cody.haievents.common.util.Result



internal interface ShowRepository {
    suspend fun showDetails(showId: Int): Result<ShowEventResponse>
    suspend fun ticketPrice(showId: Int): Result<getShowTicketResponse>
}

