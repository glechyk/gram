package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface CachePasswordUseCase {

    suspend fun execute(password: String?)

}
