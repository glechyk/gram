package com.glechyk.gram.delta.authFeature.impl.presentation.model

sealed class PasswordConfirmationUiState {

    object Empty : PasswordConfirmationUiState()

    object Success : PasswordConfirmationUiState()

    object Error : PasswordConfirmationUiState()
}
