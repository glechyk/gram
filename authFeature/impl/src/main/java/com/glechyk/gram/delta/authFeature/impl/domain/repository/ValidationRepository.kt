package com.glechyk.gram.delta.authFeature.impl.domain.repository

import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordConfirmationStateDomain
import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordValidationStateDomain
import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.authFeature.impl.domain.model.TimerStateDomain
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationType
import kotlinx.coroutines.flow.Flow

interface ValidationRepository {

    val passwordValidationFlow: Flow<PasswordValidationStateDomain>

    val passwordConfirmationFlow: Flow<PasswordConfirmationStateDomain>

    val timerFlow: Flow<TimerStateDomain>

    suspend fun validateLocalEmail(email: String): ResultDomain<ValidationType>

    suspend fun validateNetworkEmail(email: String): ResultDomain<ValidationType>

    suspend fun validateLocalPhoneNumber(phoneNumber: String): ResultDomain<ValidationType>

    suspend fun validateNetworkPhoneNumber(phoneNumber: String): ResultDomain<ValidationType>

    suspend fun validatePassword(password: String)

    suspend fun validatePasswordConfirmation(password: String, confirmation: String)

    suspend fun validateNickname(nickname: String): ResultDomain<String>

    suspend fun validateFullName(fullName: String): ResultDomain<String>

    fun startTimer(timeOfTimer: Long)

    fun getCurrentTimerState() : TimerStateDomain

}
