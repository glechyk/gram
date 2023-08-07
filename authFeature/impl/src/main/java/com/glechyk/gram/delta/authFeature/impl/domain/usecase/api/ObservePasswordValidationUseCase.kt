package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordValidationStateDomain
import kotlinx.coroutines.flow.Flow

interface ObservePasswordValidationUseCase {

    fun execute(): Flow<PasswordValidationStateDomain>

}
