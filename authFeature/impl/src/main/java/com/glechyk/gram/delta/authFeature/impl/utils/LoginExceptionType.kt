package com.glechyk.gram.delta.authFeature.impl.utils

import com.chi.interngram.delta.authFeature.impl.R
import com.glechyk.gram.delta.core.utils.UiText

enum class LoginExceptionType(val uiText: UiText) {
    InvalidInput(UiText.StringResource(R.string.network_validation_error_login_invalid)),
    IncorrectInput(UiText.StringResource(R.string.network_validation_error_login_incorrect))
}
