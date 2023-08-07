package com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl

import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedNicknameUseCase

class ObserveCachedNicknameUseCaseImpl(
    private val repository: AuthRepository
) : ObserveCachedNicknameUseCase {

    override fun execute() = repository.nicknameFlow

}
