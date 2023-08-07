package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface CacheEmailUseCase {

    suspend fun execute(email: String?)

}
