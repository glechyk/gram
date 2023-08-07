package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordConfirmationStateDomain
import kotlinx.coroutines.flow.Flow

interface ObservePasswordConfirmationUseCase {

    fun execute(): Flow<PasswordConfirmationStateDomain>

}
