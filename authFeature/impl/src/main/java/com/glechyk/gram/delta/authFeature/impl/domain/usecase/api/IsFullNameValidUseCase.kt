package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain

interface IsFullNameValidUseCase {

    suspend fun execute(fullName: String): ResultDomain<String>

}
