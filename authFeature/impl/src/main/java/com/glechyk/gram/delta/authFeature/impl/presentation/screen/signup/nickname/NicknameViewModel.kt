package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.nickname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheNicknameUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsNicknameValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedNicknameUseCase
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toNicknameUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.NicknameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NicknameViewModel(
    private val isNicknameValidUseCase: IsNicknameValidUseCase,
    private val cacheNicknameUseCase: CacheNicknameUseCase,
    observeCachedNicknameUseCase: ObserveCachedNicknameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NicknameUiState>(NicknameUiState.Empty)
    val uiState: StateFlow<NicknameUiState> = _uiState

    val nicknameFlow = observeCachedNicknameUseCase.execute()

    fun validate(nickname: String) = viewModelScope.launch {
        val result = isNicknameValidUseCase.execute(nickname)
        _uiState.emit(result.toNicknameUiState())
    }

    fun saveNickname(nickname: String?) = viewModelScope.launch {
        cacheNicknameUseCase.execute(nickname)
    }
}
