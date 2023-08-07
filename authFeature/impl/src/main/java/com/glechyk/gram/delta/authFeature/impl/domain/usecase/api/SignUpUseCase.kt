package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain

interface SignUpUseCase {

    suspend fun execute(): ResultDomain<Unit>

}
