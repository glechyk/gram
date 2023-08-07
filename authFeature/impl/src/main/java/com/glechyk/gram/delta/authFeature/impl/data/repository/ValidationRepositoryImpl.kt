package com.glechyk.gram.delta.authFeature.impl.data.repository

import android.os.CountDownTimer
import com.chi.interngram.delta.authFeature.impl.R
import com.glechyk.gram.delta.authFeature.impl.data.network.api.AuthApi
import com.glechyk.gram.delta.authFeature.impl.data.network.model.NicknameValidationRequest
import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordConfirmationStateDomain
import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordValidationStateDomain
import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.authFeature.impl.domain.model.TimerStateDomain
import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.utils.PasswordValidator
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationException
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationType
import com.glechyk.gram.delta.authFeature.impl.utils.isHasAtLeastOneDigit
import com.glechyk.gram.delta.authFeature.impl.utils.isHasAtLeastOneLowerCaseLetter
import com.glechyk.gram.delta.authFeature.impl.utils.isHasAtLeastOneSymbol
import com.glechyk.gram.delta.authFeature.impl.utils.isHasAtLeastOneUpperCaseLetter
import com.glechyk.gram.delta.authFeature.impl.utils.isNotEmail
import com.glechyk.gram.delta.authFeature.impl.utils.isNotFullName
import com.glechyk.gram.delta.authFeature.impl.utils.isNotNickname
import com.glechyk.gram.delta.authFeature.impl.utils.isNotPhoneNumber
import com.glechyk.gram.delta.core.utils.UiText
import com.glechyk.gram.delta.core.utils.network.toResultDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ValidationRepositoryImpl(
    private val authApi: AuthApi
) : ValidationRepository {

    private val passwordValidationStateFlow =
        MutableStateFlow<PasswordValidationStateDomain>(PasswordValidationStateDomain.Empty)
    override val passwordValidationFlow: Flow<PasswordValidationStateDomain> =
        passwordValidationStateFlow

    private val passwordConfirmationStateFlow =
        MutableStateFlow<PasswordConfirmationStateDomain>(PasswordConfirmationStateDomain.Empty)
    override val passwordConfirmationFlow: Flow<PasswordConfirmationStateDomain> =
        passwordConfirmationStateFlow

    private val timerStateFlow = MutableStateFlow<TimerStateDomain>(TimerStateDomain.Finish)
    override val timerFlow: Flow<TimerStateDomain> = timerStateFlow

    private lateinit var timer: CountDownTimer

    override suspend fun validateLocalEmail(email: String): ResultDomain<ValidationType> = when {
        email.isNotEmail() && email.isNotEmpty() -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_email)
            )
        )
        else -> ResultDomain.Success(ValidationType.Local(email))
    }

    override suspend fun validateNetworkEmail(email: String): ResultDomain<ValidationType> = runCatching {
        val response = authApi.validateEmail(email = email)
        response.toResultDomain<ValidationType>(
            onSuccess = {
                ResultDomain.Success(ValidationType.Network(email))
            },
            error400UiText = UiText.StringResource(R.string.validation_error_email),
            error409UiText = UiText.StringResource(R.string.network_validation_error_email)
        )
    }.getOrElse {
        ResultDomain.Error(it)
    }

    override suspend fun validateLocalPhoneNumber(phoneNumber: String): ResultDomain<ValidationType> =
        when {
            phoneNumber.isNotPhoneNumber() && phoneNumber.isNotEmpty() -> ResultDomain.Error(
                ValidationException(
                    UiText.StringResource(R.string.validation_error_phone)
                )
            )
            else -> ResultDomain.Success(ValidationType.Local(phoneNumber))
        }

    override suspend fun validateNetworkPhoneNumber(phoneNumber: String): ResultDomain<ValidationType> = runCatching{
        val response = authApi.validatePhoneNumber(phoneNumber = phoneNumber)
        response.toResultDomain<ValidationType>(
            onSuccess = {
                ResultDomain.Success(ValidationType.Network(phoneNumber))
            },
            error400UiText = UiText.StringResource(R.string.validation_error_phone),
            error409UiText = UiText.StringResource(R.string.network_validation_error_phone)
        )
    }.getOrElse {
        ResultDomain.Error(it)
    }

    override suspend fun validatePassword(password: String) {
        passwordValidationStateFlow.emit(
            if (password.isEmpty()) PasswordValidationStateDomain.Empty
            else PasswordValidator(
                minEightChar = password.length >= 8,
                minOneUpperCase = password.isHasAtLeastOneUpperCaseLetter(),
                minOneLowerCase = password.isHasAtLeastOneLowerCaseLetter(),
                minOneDigit = password.isHasAtLeastOneDigit(),
                minOneSpecChar = password.isHasAtLeastOneSymbol()
            ).validate()
        )
    }

    override suspend fun validatePasswordConfirmation(password: String, confirmation: String) {
        passwordConfirmationStateFlow.emit(
            if (confirmation.isEmpty()) PasswordConfirmationStateDomain.Empty
            else if (
                passwordValidationStateFlow.value !is PasswordValidationStateDomain.Success ||
                password != confirmation
            ) PasswordConfirmationStateDomain.Error
            else PasswordConfirmationStateDomain.Success
        )
    }

    override suspend fun validateNickname(nickname: String): ResultDomain<String> = when {
        nickname.length < 8 && nickname.isNotBlank() -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_nickname_less_8)
            )
        )
        nickname.length > 20 -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_nickname_more_than_20)
            )
        )
        nickname.isNotNickname() && nickname.isNotEmpty() -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_nickname_forbidden_symbols)
            )
        )
        else -> {
            runCatching {
                val response =
                    authApi.validateNicknameForUniqueness(NicknameValidationRequest(nickname = nickname))

                response.toResultDomain(
                    onSuccess = {
                        ResultDomain.Success(nickname)
                    },
                    error400UiText = UiText.StringResource(R.string.network_validation_error_nickname)
                )
            }.getOrElse {
                ResultDomain.Error(it)
            }
        }
    }

    override suspend fun validateFullName(fullName: String): ResultDomain<String> = when {
        fullName.length < 2 && fullName.isNotEmpty() -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_full_name_less_2)
            )
        )
        fullName.length > 35 -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_full_name_more_than_35)
            )
        )
        fullName.isNotFullName() && fullName.isNotEmpty() -> ResultDomain.Error(
            ValidationException(
                UiText.StringResource(R.string.validation_error_full_name_forbidden_symbols)
            )
        )
        else -> ResultDomain.Success(fullName)
    }

    override fun startTimer(timeOfTimer: Long) {
        runCatching { timer.cancel() }
        timer = object : CountDownTimer(timeOfTimer, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                timerStateFlow.value = TimerStateDomain.Tick(millisUntilFinished)
            }

            override fun onFinish() {
                timerStateFlow.value = TimerStateDomain.Finish
            }
        }
        timerStateFlow.value = TimerStateDomain.Start(timeOfTimer)
        timer.start()
    }

    override fun getCurrentTimerState() = timerStateFlow.value

}
