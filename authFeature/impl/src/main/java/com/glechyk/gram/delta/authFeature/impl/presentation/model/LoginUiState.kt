package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.core.utils.UiText

sealed class LoginUiState {

    object Success : LoginUiState()

    data class Error(val uiText: UiText) : LoginUiState()

    object NoInternetError : LoginUiState()

    data class UnknownError(val message: String) : LoginUiState()

    object ServerError : LoginUiState()


}
