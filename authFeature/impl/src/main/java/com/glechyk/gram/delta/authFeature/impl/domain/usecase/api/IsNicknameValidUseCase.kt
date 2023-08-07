package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.core.utils.ResultDomain

interface IsNicknameValidUseCase {

    suspend fun execute(nickname: String): ResultDomain<String>

}
