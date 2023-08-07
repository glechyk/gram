package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.authFeature.impl.utils.PasswordValidator

sealed class PasswordUiState {

    object Empty : PasswordUiState()

    data class Success(val validator: PasswordValidator) : PasswordUiState()

    object Loading : PasswordUiState()

    data class Error(val validator: PasswordValidator?) : PasswordUiState()
}
