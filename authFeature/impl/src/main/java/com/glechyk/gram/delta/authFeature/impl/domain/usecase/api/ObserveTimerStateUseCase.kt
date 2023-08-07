package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.authFeature.impl.domain.model.TimerStateDomain
import kotlinx.coroutines.flow.Flow

interface ObserveTimerStateUseCase {

    fun execute(): Flow<TimerStateDomain>

}
