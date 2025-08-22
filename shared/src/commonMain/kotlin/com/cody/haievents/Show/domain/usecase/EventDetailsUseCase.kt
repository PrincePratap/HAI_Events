package com.cody.haievents.show.domain.usecase

import com.cody.haievents.common.util.Result

class EventDetailsUseCase {



    operator fun invoke(
        eventTitle: String,
        organiserName: String,
        contactEmail: String,
        eventLocation: String,
        eventDate: String,       // only required, no format check
        eventTime: String,       // only required, no format check
        eventDescription: String,
        ticketType: String,
        ticketQuantity: String,  // numeric
        ticketPrice: String      // decimal
    ): Result<Unit> {




        return Result.Success(Unit)
    }



}
