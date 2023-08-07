package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CachePasswordUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPasswordConfirmationValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPasswordValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedPasswordUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObservePasswordConfirmationUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObservePasswordValidationUseCase
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toPasswordConfirmationUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toPasswordValidationUiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PasswordViewModel(
    private val isPasswordValidUseCase: IsPasswordValidUseCase,
    private val isPasswordConfirmationValidUseCase: IsPasswordConfirmationValidUseCase,
    private val cachePasswordUseCase: CachePasswordUseCase,
    observePasswordValidationUseCase: ObservePasswordValidationUseCase,
    observePasswordConfirmationUseCase: ObservePasswordConfirmationUseCase,
    observeCachedPasswordUseCase: ObserveCachedPasswordUseCase
) : ViewModel() {

    val validationUiState =
        observePasswordValidationUseCase.execute().map { it.toPasswordValidationUiState() }

    val confirmationUiState =
        observePasswordConfirmationUseCase.execute().map { it.toPasswordConfirmationUiState() }

    val passwordFlow = observeCachedPasswordUseCase.execute()

    fun validatePassword(password: String) = viewModelScope.launch {
        isPasswordValidUseCase.execute(password)
    }

    fun validatePasswordConfirmation(password: String, confirmation: String) =
        viewModelScope.launch {
            isPasswordConfirmationValidUseCase.execute(password, confirmation)
        }

    fun savePassword(password: String?) = viewModelScope.launch {
        cachePasswordUseCase.execute(password)
    }
}
