package com.cody.haievents.android.screens.myTickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cody.haievents.android.common.components.CommonTopBar
import com.cody.haievents.android.common.componets.card.BlogPostCard
import com.cody.haievents.android.common.componets.card.MyTicket
import com.cody.haievents.android.screens.Blogs.BlogsUiState


@Composable
fun MyTicketsScreen(
    uiState: MyTicketsUiState = MyTicketsUiState(),
    onTicketClick: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CommonTopBar(title = " My Tickets" , showBackButton = false)
        }


    )
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            uiState.myTicketList?.let {
                items(it.bookings) { ticket ->
                    MyTicket(
                        imageUrl = "https://haievents.com/"+ticket.data.imagePath,
                        title = ticket.data.title,
                        venue = ticket.data.venue,
                        date = ticket.data.date,
                        time = ticket.data.time,
                        onClick = {
                            onTicketClick(ticket.data.id)
                    })
                }
            }
        }
    }
}