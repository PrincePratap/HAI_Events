package com.cody.haievents.android.screens.GaneshTheater

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cody.haievents.Show.domain.usecase.GaneshTheaterUseCase
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.android.main.MainActivity
import com.cody.haievents.common.util.Result
import com.cody.haievents.phonepe.domain.usecase.PhonePeGaneshTheatreUseCase
import com.cody.haievents.phonepe.model.Meta
import com.cody.haievents.phonepe.model.PaymentResponseGaneshTheatre
import com.cody.haievents.phonepe.model.Seat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GaneshTheaterViewModel(
    private val useCase: GaneshTheaterUseCase,
    private val paymentUseCase: PhonePeGaneshTheatreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GaneshTheaterUiState())

    val uiState = _uiState.asStateFlow()

    fun getGaneshTheater() {
        Log.i(TAG, "getGaneshTheater: start")
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result = useCase()) {
                is Result.Error -> {
                    Log.e(TAG, "getGaneshTheater: error -> ${result.message}")
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                is Result.Success -> {
                    val seats = result.data?.seatConfig ?: emptyList()
                    Log.i(TAG, "getGaneshTheater: success -> seats=${seats.size}")
                    _uiState.update {
                        it.copy(isLoading = false, succeed = true, seatConfig = result.data)
                    }
                }
            }
        }
    }

    fun makePayment(totalAmount: String) {
        val amount = 1
        val meta = Meta(
            seats = listOf(Seat("Y", 42), Seat("U", 44)),
        )

        Log.i(TAG, "makePayment: start -> amount=$amount, seats=${meta.seats.size}")

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = paymentUseCase(amount = amount, meta = meta)) {
                is Result.Error -> {
                    Log.e(TAG, "makePayment: create-order FAILED -> ${result.message}")
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                is Result.Success -> {
                    val orderId = result.data?.merchantOrderId
                    val phonepeToken = result.data?.token
                    Log.i(
                        TAG, "makePayment: create-order OK -> orderId=$orderId, hasTokenParam=$phonepeToken"
                    )
                    _uiState.update {
                        // Expose response; Activity will call PhonePe SDK and then we’ll clear it
                        it.copy(isLoading = false, succeed = true, paymentResponse = result.data)
                    }
                    Log.d(TAG, "makePayment: paymentResponse posted to UI state")
                }
            }
        }
    }

    /** Call after Activity consumes paymentResponse to avoid re-triggering on config changes. */
    fun clearPaymentTrigger() {
        Log.d(TAG, "clearPaymentTrigger: clearing paymentResponse trigger")
        _uiState.update { it.copy(paymentResponse = null) }
    }

    companion object {
        private const val TAG = "GaneshTheaterVM"
    }
}

data class GaneshTheaterUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val succeed: Boolean = false,
    val seatConfig: GaneshTheaterGetSeatResponse? = null,
    val paymentResponse: PaymentResponseGaneshTheatre? = null
)
