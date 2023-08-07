package com.glechyk.gram.delta.authFeature.impl.presentation.model

sealed class SendingVerificationCodeUiState {

    object Success : SendingVerificationCodeUiState()

    data class RequestLimitError(val time: Long) : SendingVerificationCodeUiState()

    object ServerError : SendingVerificationCodeUiState()

    object NoInternetError : SendingVerificationCodeUiState()

    data class UnknownError(val message: String) : SendingVerificationCodeUiState()

}
