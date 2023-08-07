package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface IsPasswordValidUseCase {

    suspend fun execute(password: String)

}
