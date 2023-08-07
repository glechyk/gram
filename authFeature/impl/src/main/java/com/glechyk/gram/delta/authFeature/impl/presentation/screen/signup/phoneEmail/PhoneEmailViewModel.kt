package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.phoneEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheEmailUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CachePhoneNumberUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetUserCachedLoginUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsEmailValidLocalUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsEmailValidNetworkUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPhoneNumberValidLocalUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPhoneNumberValidNetworkUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedEmailUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedPhoneNumberUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ResetAllCacheUseCase
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toPhoneEmailUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toTab
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PhoneEmailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhoneEmailViewModel(
    private val isPhoneNumberValidLocalUseCase: IsPhoneNumberValidLocalUseCase,
    private val isPhoneNumberValidNetworkUseCase: IsPhoneNumberValidNetworkUseCase,
    private val isEmailValidLocalUseCase: IsEmailValidLocalUseCase,
    private val isEmailValidNetworkUseCase: IsEmailValidNetworkUseCase,
    private val cachePhoneNumberUseCase: CachePhoneNumberUseCase,
    private val cacheEmailUseCase: CacheEmailUseCase,
    private val getUserCachedLoginUseCase: GetUserCachedLoginUseCase,
    private val resetAllCacheUseCase: ResetAllCacheUseCase,
    observeCachedPhoneNumberUseCase: ObserveCachedPhoneNumberUseCase,
    observeCachedEmailUseCase: ObserveCachedEmailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PhoneEmailUiState>(PhoneEmailUiState.Empty)
    val uiState: StateFlow<PhoneEmailUiState> = _uiState

    val phoneNumberFlow = observeCachedPhoneNumberUseCase.execute()

    val emailFlow = observeCachedEmailUseCase.execute()

    fun validateLocalPhoneNumber(phoneNumber: String) = viewModelScope.launch {
        val result = isPhoneNumberValidLocalUseCase.execute(phoneNumber)
        _uiState.emit(result.toPhoneEmailUiState())
    }

    fun validateNetworkPhoneNumber(phoneNumber: String) = viewModelScope.launch {
        _uiState.emit(PhoneEmailUiState.Loading)
        val result = isPhoneNumberValidNetworkUseCase.execute(phoneNumber)
        _uiState.emit(result.toPhoneEmailUiState())
    }

    fun validateLocalEmail(email: String) = viewModelScope.launch {
        val result = isEmailValidLocalUseCase.execute(email)
        _uiState.emit(result.toPhoneEmailUiState())
    }

    fun validateNetworkEmail(email: String) = viewModelScope.launch {
        _uiState.emit(PhoneEmailUiState.Loading)
        val result = isEmailValidNetworkUseCase.execute(email)
        _uiState.emit(result.toPhoneEmailUiState())
    }

    fun savePhoneNumber(phoneNumber: String?) = viewModelScope.launch {
        cachePhoneNumberUseCase.execute(phoneNumber)
    }

    fun saveEmail(email: String?) = viewModelScope.launch {
        cacheEmailUseCase.execute(email)
    }

    fun resetAllCache() = viewModelScope.launch {
        resetAllCacheUseCase.execute()
    }

    fun getCurrentTab() = getUserCachedLoginUseCase.execute().toTab()

}
