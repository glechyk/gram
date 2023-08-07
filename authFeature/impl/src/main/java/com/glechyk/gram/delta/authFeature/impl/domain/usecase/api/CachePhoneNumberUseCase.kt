package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface CachePhoneNumberUseCase {

    suspend fun execute(phoneNumber: String?)

}
