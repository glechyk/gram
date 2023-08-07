package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationType

interface IsEmailValidLocalUseCase {

    suspend fun execute(email: String): ResultDomain<ValidationType>

}
