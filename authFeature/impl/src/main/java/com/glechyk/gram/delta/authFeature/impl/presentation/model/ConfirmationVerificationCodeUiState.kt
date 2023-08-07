package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.core.utils.UiText

sealed class ConfirmationVerificationCodeUiState {

    object Success : ConfirmationVerificationCodeUiState()

    data class Error(val uiText: UiText) : ConfirmationVerificationCodeUiState()

    data class RequestLimitError(val time: Long) : ConfirmationVerificationCodeUiState()

    object ServerError : ConfirmationVerificationCodeUiState()

    object NoInternetError : ConfirmationVerificationCodeUiState()

    data class UnknownError(val message: String) : ConfirmationVerificationCodeUiState()

}
