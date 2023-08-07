package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.core.utils.UiText

sealed class NicknameUiState {

    object Success : NicknameUiState()

    data class Error(val uiText: UiText) : NicknameUiState()

    object ServerError : NicknameUiState()

    object NoInternetError : NicknameUiState()

    data class UnknownError(val message: String) : NicknameUiState()

    object Empty : NicknameUiState()

}
