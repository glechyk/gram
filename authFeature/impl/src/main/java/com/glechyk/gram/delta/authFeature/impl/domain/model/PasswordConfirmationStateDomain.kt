package com.glechyk.gram.delta.authFeature.impl.domain.model

sealed class PasswordConfirmationStateDomain {

    object Empty : PasswordConfirmationStateDomain()

    object Success : PasswordConfirmationStateDomain()

    object Error : PasswordConfirmationStateDomain()
}
