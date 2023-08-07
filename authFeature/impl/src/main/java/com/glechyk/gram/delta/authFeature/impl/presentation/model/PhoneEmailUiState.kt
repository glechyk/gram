package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.core.utils.UiText

sealed class PhoneEmailUiState {

    object Empty : PhoneEmailUiState()

    object LocalSuccess : PhoneEmailUiState()

    object NetworkSuccess : PhoneEmailUiState()

    object Loading : PhoneEmailUiState()

    data class Error(val uiText: UiText) : PhoneEmailUiState()

    object ServerError : PhoneEmailUiState()

    object NoInternetError : PhoneEmailUiState()

    data class UnknownError(val message: String) : PhoneEmailUiState()
}
