package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface IsPasswordConfirmationValidUseCase {

    suspend fun execute(password: String, confirmation: String)

}
