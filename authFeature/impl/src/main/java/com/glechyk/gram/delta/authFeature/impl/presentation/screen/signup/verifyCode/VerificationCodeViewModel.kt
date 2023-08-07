package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.verifyCode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetCurrentTimerStateUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetUserCachedLoginUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.StartVerificationCodeTimerUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsVerificationCodeConfirmedUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveTimerStateUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.SendVerificationCodeUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.SignUpUseCase
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toSendingVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toTimerUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toConfirmationVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toSignUpUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.SendingVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.TimerUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.ConfirmationVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.SignUpUiState
import com.glechyk.gram.delta.core.utils.Constants
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VerificationCodeViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val getUserCachedLoginUseCase: GetUserCachedLoginUseCase,
    private val sendVerificationCodeUseCase: SendVerificationCodeUseCase,
    private val isVerificationCodeConfirmedUseCase: IsVerificationCodeConfirmedUseCase,
    private val startVerificationCodeTimerUseCase: StartVerificationCodeTimerUseCase,
    private val getCurrentTimerStateUseCase: GetCurrentTimerStateUseCase,
    observeTimerStateUseCase: ObserveTimerStateUseCase
) : ViewModel() {

    init {
        if (getCurrentTimerState() is TimerUiState.Finish) {
            sendVerificationCode()
        }
    }

    private val _sendingState = MutableSharedFlow<SendingVerificationCodeUiState>()
    val sendingState: SharedFlow<SendingVerificationCodeUiState> = _sendingState

    private val _confirmationState = MutableSharedFlow<ConfirmationVerificationCodeUiState>()
    val confirmationState: SharedFlow<ConfirmationVerificationCodeUiState> = _confirmationState

    private val _signUpState = MutableSharedFlow<SignUpUiState>()
    val signUpState: SharedFlow<SignUpUiState> = _signUpState

    val timerFlow = observeTimerStateUseCase.execute().map { it.toTimerUiState() }

    fun getUserCachedLogin() = getUserCachedLoginUseCase.execute()

    fun userInputToCode(string: String): String {
        val list = string.trim().toCharArray().toMutableList()
        if (list.size < 6) {
            repeat(6 - list.size) {
                list.add('_')
            }
        }
        return list.joinToString(" ").trim()
    }

    fun sendVerificationCode() = viewModelScope.launch {
        val result = sendVerificationCodeUseCase.execute().toSendingVerificationCodeUiState()
        if(result !is SendingVerificationCodeUiState.NoInternetError) {
            startTimer(Constants.MINUTE_IN_MILLIS)
        }
        _sendingState.emit(result)
    }

    fun confirmCode(code: String) = viewModelScope.launch {
        val result =
            isVerificationCodeConfirmedUseCase.execute(code).toConfirmationVerificationCodeUiState()
        _confirmationState.emit(result)
    }

    fun signUpUser() = viewModelScope.launch {
        val result = signUpUseCase.execute().toSignUpUiState()
        _signUpState.emit(result)
    }

    fun startTimer(timeOfTimer: Long) = startVerificationCodeTimerUseCase.execute(timeOfTimer)

    private fun getCurrentTimerState() = getCurrentTimerStateUseCase.execute().toTimerUiState()

}
