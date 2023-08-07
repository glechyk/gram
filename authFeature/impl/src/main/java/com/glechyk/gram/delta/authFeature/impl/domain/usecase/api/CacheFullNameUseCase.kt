package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface CacheFullNameUseCase {

    suspend fun execute(fullName: String?)

}
