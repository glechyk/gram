package com.glechyk.gram.delta.authFeature.impl.presentation.model

import com.glechyk.gram.delta.core.utils.UiText

sealed class FullNameUiState {

    object Success : FullNameUiState()

    data class Error(val uiText: UiText) : FullNameUiState()

    object Empty : FullNameUiState()

}
