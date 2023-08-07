package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.authFeature.impl.utils.UserCachedLogin

interface GetUserCachedLoginUseCase {

    fun execute(): UserCachedLogin

}
