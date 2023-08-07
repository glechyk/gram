package com.glechyk.gram.delta.authFeature.impl.utils

import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordValidationStateDomain

data class PasswordValidator(
    val minEightChar: Boolean = false,
    val minOneUpperCase: Boolean = false,
    val minOneLowerCase: Boolean = false,
    val minOneDigit: Boolean = false,
    val minOneSpecChar: Boolean = false
) {
    val isSuccess: Boolean
        get() = minEightChar && minOneUpperCase && minOneLowerCase && minOneDigit && minOneSpecChar

    fun validate() =
        if (isSuccess) {
            PasswordValidationStateDomain.Success(this)
        } else {
            PasswordValidationStateDomain.Error(this)
        }
}
