package com.glechyk.gram.delta.splashFeature.impl.presentation.screen.splash

import androidx.lifecycle.ViewModel
import com.glechyk.gram.delta.splashFeature.impl.domain.usecase.api.CheckUserAuthenticationUseCase
import com.glechyk.gram.delta.splashFeature.impl.presentation.mappers.toAuthenticationPresentation
import com.glechyk.gram.delta.splashFeature.impl.presentation.model.AuthenticationPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel(
    checkUserAuthenticationUseCase: CheckUserAuthenticationUseCase
) : ViewModel() {

    private val _authentication = MutableStateFlow(
        checkUserAuthenticationUseCase.execute().toAuthenticationPresentation()
    )

    val authentication: StateFlow<AuthenticationPresentation> = _authentication

}
