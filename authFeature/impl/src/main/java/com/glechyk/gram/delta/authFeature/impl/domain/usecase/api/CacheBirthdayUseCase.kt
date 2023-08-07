package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface CacheBirthdayUseCase {

    suspend fun execute(date: Long?)

}
