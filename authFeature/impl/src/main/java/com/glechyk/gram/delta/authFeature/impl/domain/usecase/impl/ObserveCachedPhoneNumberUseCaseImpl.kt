package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedPhoneNumberUseCase

class ObserveCachedPhoneNumberUseCaseImpl(
    private val repository: AuthRepository
) : ObserveCachedPhoneNumberUseCase {

    override fun execute() = repository.phoneNumberFlow

}
