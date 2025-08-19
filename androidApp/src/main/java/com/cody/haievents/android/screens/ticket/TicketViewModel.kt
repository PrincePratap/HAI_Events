package com.cody.haievents.android.screens.ticket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.data.TicketType
import com.cody.haievents.Show.data.model.OrderResponse
import com.cody.haievents.Show.domain.usecase.TicketListUseCase
import com.cody.haievents.Show.domain.usecase.createOrderUseCase
import com.cody.haievents.common.util.Result
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
    private val createOrderUseCase: createOrderUseCase
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
        Log.d(TAG, "create order called: $_stateFlow.value.totalPrice")

        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoading = true, errorMessage = null) }

            when (val res = createOrderUseCase(_stateFlow.value.totalPrice.toString())) {
                is Result.Error -> {
                    Log.d(TAG, "create order called: ${res.message}")

                    _stateFlow.update { it.copy(isLoading = false, errorMessage = res.message) }
                }
                is Result.Success -> {
                    Log.d(TAG, "create order called: ${res.data}")

                    // Reset selections for new list
                    _stateFlow.update { it.copy(isLoading = false, succeed = true) }
                    _events.send(UiEvent.OrderCreated(res.data)) // pass along payload if needed
                }
            }
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
