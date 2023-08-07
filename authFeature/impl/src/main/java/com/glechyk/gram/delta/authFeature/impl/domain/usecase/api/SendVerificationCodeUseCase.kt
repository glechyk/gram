package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain

interface SendVerificationCodeUseCase {

    suspend fun execute() : ResultDomain<Unit>

}
