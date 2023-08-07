package com.glechyk.gram.delta.authFeature.impl.domain.model

import com.glechyk.gram.delta.authFeature.impl.utils.PasswordValidator

sealed class PasswordValidationStateDomain {

    object Empty : PasswordValidationStateDomain()

    data class Success(val validator: PasswordValidator) : PasswordValidationStateDomain()

    data class Error(val validator: PasswordValidator) : PasswordValidationStateDomain()
}
