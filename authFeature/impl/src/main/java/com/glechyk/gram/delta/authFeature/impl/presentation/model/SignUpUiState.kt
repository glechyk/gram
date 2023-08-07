package com.glechyk.gram.delta.authFeature.impl.presentation.model

sealed class SignUpUiState {

    object Success : SignUpUiState()

    object NoInternetError : SignUpUiState()

    data class UnknownError(val message: String) : SignUpUiState()

    object ServerError : SignUpUiState()

}
