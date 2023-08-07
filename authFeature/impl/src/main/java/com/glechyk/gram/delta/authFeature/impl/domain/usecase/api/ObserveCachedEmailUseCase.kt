package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import kotlinx.coroutines.flow.Flow

interface ObserveCachedEmailUseCase {

    fun execute(): Flow<String>

}
