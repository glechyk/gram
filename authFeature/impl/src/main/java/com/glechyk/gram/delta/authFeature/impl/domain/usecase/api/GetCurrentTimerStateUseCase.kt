package com.glechyk.gram.delta.authFeature.impl.domain.usecase.api

import com.glechyk.gram.delta.authFeature.impl.domain.model.TimerStateDomain

interface GetCurrentTimerStateUseCase {

    fun execute(): TimerStateDomain

}
