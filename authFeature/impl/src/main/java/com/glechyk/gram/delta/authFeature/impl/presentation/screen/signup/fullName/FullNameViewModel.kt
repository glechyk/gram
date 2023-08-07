package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.fullName

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheFullNameUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsFullNameValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedFullNameUseCase
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toFullNameUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.FullNameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FullNameViewModel(
    private val isFullNameValidUseCase: IsFullNameValidUseCase,
    private val cacheFullNameUseCase: CacheFullNameUseCase,
    observeCachedFullNameUseCase: ObserveCachedFullNameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<FullNameUiState>(FullNameUiState.Empty)
    val uiState: StateFlow<FullNameUiState> = _uiState

    val fullNameFlow = observeCachedFullNameUseCase.execute()

    fun validate(fullName: String) = viewModelScope.launch {
        val result = isFullNameValidUseCase.execute(fullName)
        _uiState.emit(result.toFullNameUiState())
    }

    fun saveFullName(fullName: String?) = viewModelScope.launch {
        cacheFullNameUseCase.execute(fullName)
    }

}
