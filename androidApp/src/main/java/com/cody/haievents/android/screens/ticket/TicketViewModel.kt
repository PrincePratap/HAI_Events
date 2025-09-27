package com.cody.haievents.android.screens.ticket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.data.TicketType
import com.cody.haievents.Show.model.OrderResponse
import com.cody.haievents.Show.domain.usecase.TicketListUseCase
import com.cody.haievents.Show.domain.usecase.createOrderUseCase
import com.cody.haievents.android.screens.GaneshTheater.GaneshTheaterViewModel
import com.cody.haievents.android.screens.GaneshTheater.GaneshTheaterViewModel.Companion
import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.domain.usecase.BuyTicketUseCase
import com.cody.haievents.phonepe.domain.usecase.TicketPurchaseUseCase
import com.cody.haievents.phonepe.model.BuyTicketRequest
import com.cody.haievents.phonepe.model.PhonePeTicketRequestResponse
import com.cody.haievents.phonepe.model.Ticket
import com.cody.haievents.phonepe.model.TicketLine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.channels.Channel



class TicketViewModel(
    private val useCase: TicketListUseCase,
    private val ticketPurchaseUseCase: TicketPurchaseUseCase,
    private val buyTicketUseCase: BuyTicketUseCase

) : ViewModel() {

    companion object {
        private const val TAG = "TicketViewModel"
    }

    private val _stateFlow = MutableStateFlow(TicketUiState())
    val stateFlow: StateFlow<TicketUiState> = _stateFlow

    // one-shot UI events
    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events: Flow<UiEvent> = _events.receiveAsFlow()

    init {
        Log.d(TAG, "ViewModel initialized")
    }




    fun getTicketList(showId: Int) {
        Log.d(TAG, "getTicketList called for showId: $showId")

        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true, errorMessage = null) }

            when (val res = useCase(showId)) {
                is Result.Error -> {
                    Log.e(TAG, "Failed to fetch tickets: ${res.message}")
                    _stateFlow.update { it.copy(isLoading = false, errorMessage = res.message) }
                }
                is Result.Success -> {
                    val tickets = res.data?.ticketTypes.orEmpty()
                    Log.d(TAG, "Successfully fetched ${tickets.size} tickets")

                    // Reset selections for new list
                    val initialSelection = tickets.associate { it.id to 0 }
                    _stateFlow.update {
                        it.copy(
                            isLoading = false,
                            succeed = true,
                            ticketList = tickets,
                            selectedQuantities = initialSelection
                        )
                    }
                    recalcTotals()
                }
            }
        }
    }

    fun createOrder() {
        Log.d(TAG, "createOrder called: totalPrice=${_stateFlow.value.totalPrice}")

        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true, errorMessage = null) }

            val ticketsForRequest = buildSelectedTicketsForRequest()
            if (ticketsForRequest.isEmpty()) {
                _stateFlow.update { it.copy(isLoading = false, errorMessage = "Please select at least one ticket") }
                _events.send(UiEvent.ShowToast("Select at least one ticket"))
                return@launch
            }

            val amount = _stateFlow.value.totalPrice
            if (amount <= 0) {
                _stateFlow.update { it.copy(isLoading = false, errorMessage = "Amount must be greater than 0") }
                _events.send(UiEvent.ShowToast("Amount must be greater than 0"))
                return@launch
            }

            when (val res = ticketPurchaseUseCase(
                amount = amount,
                tickets = ticketsForRequest
            )) {
                is Result.Error -> {
                    Log.e(TAG, "Ticket purchase failed: ${res.message}")
                    _stateFlow.update { it.copy(isLoading = false, errorMessage = res.message) }
                    _events.send(UiEvent.ShowToast(res.message ?: "Purchase failed"))
                }
                is Result.Success -> {
                    Log.i(TAG, "Ticket purchase success: ${res.data}")
                    _stateFlow.update { it.copy(isLoading = false, succeed = true , createPhoneOrderResponse = res.data) }

                }
            }
        }
    }
    // In TicketViewModel

    fun buyTickets() { // renamed from buyTicketUseCase() to avoid name clash with property
        Log.d(TAG, "buyTickets called")

        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true, errorMessage = null) }

            val state = _stateFlow.value

            // 1) Build lines from selection
            val lines: List<TicketLine> =
                state.ticketList.mapNotNull { tt ->
                    val qty = state.selectedQuantities[tt.id] ?: 0
                    if (qty <= 0) return@mapNotNull null

                    val price = tt.price.toPriceDouble() // "100.00" -> 100.0
                    TicketLine(
                        ticketId = tt.id,
                        price = price,
                        quantity = qty
                    )
                }

            if (lines.isEmpty()) {
                _stateFlow.update { it.copy(isLoading = false, errorMessage = "Please select at least one ticket") }
                _events.send(UiEvent.ShowToast("Select at least one ticket"))
                return@launch
            }

            // 2) Derive item_id & item_type from first selected ticket type
            val firstSelected: TicketType? = state.ticketList.firstOrNull { (state.selectedQuantities[it.id] ?: 0) > 0 }
                ?: state.ticketList.firstOrNull()

            if (firstSelected == null) {
                _stateFlow.update { it.copy(isLoading = false, errorMessage = "No ticket types available") }
                _events.send(UiEvent.ShowToast("No ticket types available"))
                return@launch
            }

            val itemId = firstSelected.eventId



            Log.d(TAG, "itemId  $itemId itemType  $lines")

            when (val res = buyTicketUseCase(itemId = itemId ,tickets = lines )) {

                is Result.Error -> {
                    Log.e(TAG, "Buy tickets failed: ${res.message}")
                    _stateFlow.update { it.copy(isLoading = false, errorMessage = res.message) }
                    _events.send(UiEvent.ShowToast(res.message ?: "Booking failed"))
                }
                is Result.Success -> {
                    Log.i(TAG, "Buy tickets success: ${res.data}")
                    _stateFlow.update { it.copy(isLoading = false, succeed = true) }
                    _events.send(UiEvent.ShowToast(res.data?.message ?: "Booking successful"))
                    // Optionally navigate:
                    // _events.send(UiEvent.NavigateToSuccess(paymentId = "BOOKED"))
                }
            }
        }
    }

    /** Helpers **/
    private fun String.toPriceDouble(): Double {
        return try {
            this.trim()
                .removePrefix("₹")
                .replace(",", "")
                .toDouble()
        } catch (_: Exception) {
            0.0
        }
    }









    private fun buildSelectedTicketsForRequest(): List<Ticket> {
        val state = _stateFlow.value
        return state.ticketList.mapNotNull { tt ->
            val qty = state.selectedQuantities[tt.id] ?: 0
            if (qty <= 0) return@mapNotNull null
            Ticket(
                ticketTypeId = tt.id,
                quantity = qty,
            )
        }
    }



    fun updateQuantity(ticketId: Int, newQuantity: Int) {
        _stateFlow.update { state ->
            val cappedQty = newQuantity
                .coerceAtLeast(0)
                .let { qty ->
                    // Optionally cap to available stock if your API's `quantity` is stock left:
                    val ticket = state.ticketList.firstOrNull { it.id == ticketId }
                    val available = ticket?.quantity ?: Int.MAX_VALUE
                    qty.coerceAtMost(available)
                }

            val newMap = state.selectedQuantities.toMutableMap().apply { this[ticketId] = cappedQty }
            state.copy(selectedQuantities = newMap)
        }
        recalcTotals()
    }

    private fun recalcTotals() {
        val state = _stateFlow.value
        var totalTickets = 0
        var totalPriceRupees = 0

        for (ticket in state.ticketList) {
            val qty = state.selectedQuantities[ticket.id] ?: 0
            if (qty <= 0) continue

            totalTickets += qty
            val priceRupees = ticket.price.toRupeesInt() // safe parse
            totalPriceRupees += (priceRupees * qty)
        }

        _stateFlow.update { it.copy(totalTickets = totalTickets, totalPrice = totalPriceRupees) }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared")
    }

    fun clearPaymentTrigger() {
        Log.d(TAG, "clearPaymentTrigger: clearing paymentResponse trigger")
        _stateFlow.update { it.copy(createPhoneOrderResponse = null) }
    }


}

/** UI State **/
data class TicketUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val succeed: Boolean = false,

    val ticketList: List<TicketType> = emptyList(),

    // user-selected quantities by ticketId
    val selectedQuantities: Map<Int, Int> = emptyMap(),
    // derived
    val totalTickets: Int = 0,
    val totalPrice: Int = 0,
    val createPhoneOrderResponse: PhonePeTicketRequestResponse? = null,
)

sealed class UiEvent {
    data class ShowToast(val message: String): UiEvent()
    data class NavigateToSuccess(val paymentId: String): UiEvent()
    data class OrderCreated(val payload: OrderResponse?): UiEvent() // adjust type
}


/** Helpers **/
private fun String.toRupeesInt(): Int {
    return try {
        val v = this.trim()
            .removePrefix("₹")
            .replace(",", "")
            .toDouble()
        (v.roundToInt())
    } catch (_: Exception) {
        0
    }
}


