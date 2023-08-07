package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.authFeature.impl.utils.PasswordValidator

sealed class PasswordValidationUiState {

    object Empty : PasswordValidationUiState()

    data class Success(val validator: PasswordValidator) : PasswordValidationUiState()

    data class Error(val validator: PasswordValidator) : PasswordValidationUiState()
}
