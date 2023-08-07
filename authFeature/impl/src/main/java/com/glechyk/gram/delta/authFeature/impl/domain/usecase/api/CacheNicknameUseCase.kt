package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

interface CacheNicknameUseCase {

    suspend fun execute(nickname: String?)

}
