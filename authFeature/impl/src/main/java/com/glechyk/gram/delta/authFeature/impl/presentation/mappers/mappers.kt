package com.glechyk.gram.delta.authFeature.impl.presentation.mappers

import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordConfirmationStateDomain
import com.glechyk.gram.delta.authFeature.impl.domain.model.PasswordValidationStateDomain
import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.authFeature.impl.domain.model.TimerStateDomain
import com.glechyk.gram.delta.authFeature.impl.presentation.model.FullNameUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.LoginUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.NicknameUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordConfirmationUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordValidationUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PhoneEmailUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.SendingVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.TimerUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.ConfirmationVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.SignUpUiState
import com.glechyk.gram.delta.core.exception.RequestLimitException
import com.glechyk.gram.delta.authFeature.impl.utils.Tab
import com.glechyk.gram.delta.authFeature.impl.utils.UserCachedLogin
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationException
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationType
import com.glechyk.gram.delta.core.exception.NetworkException
import com.glechyk.gram.delta.core.exception.NoInternetException

fun ResultDomain<Unit>.toLoginUiState(): LoginUiState = when (this) {
    is ResultDomain.Success -> LoginUiState.Success
    is ResultDomain.Error -> {
        when (exception) {
            is NoInternetException -> LoginUiState.NoInternetError
            is NetworkException -> {
                val networkException = exception as NetworkException
                when (networkException.code) {
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_400, com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_401 -> LoginUiState.Error(
                        networkException.uiText
                    )
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_500 -> LoginUiState.ServerError
                    else -> LoginUiState.UnknownError(
                        "Network error code: ${networkException.code}"
                    )
                }
            }
            else -> LoginUiState.UnknownError(
                "Error: ${exception.message}"
            )
        }
    }
}

fun ResultDomain<ValidationType>.toPhoneEmailUiState(): PhoneEmailUiState = when (this) {

    is ResultDomain.Success -> when (data) {
        is ValidationType.Local -> {
            when {
                (data as ValidationType.Local).value.isEmpty() -> PhoneEmailUiState.Empty
                else -> PhoneEmailUiState.LocalSuccess
            }
        }
        is ValidationType.Network -> PhoneEmailUiState.NetworkSuccess
    }

    is ResultDomain.Error -> {
        when (exception) {
            is ValidationException -> PhoneEmailUiState.Error((exception as ValidationException).uiText)
            is NoInternetException -> PhoneEmailUiState.NoInternetError
            is NetworkException -> {
                val networkException = exception as NetworkException
                when (networkException.code) {
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_400, com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_409 -> PhoneEmailUiState.Error(
                        networkException.uiText
                    )
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_500 -> PhoneEmailUiState.ServerError
                    else -> PhoneEmailUiState.UnknownError(
                        "Network error code: ${networkException.code}"
                    )
                }
            }
            else -> PhoneEmailUiState.UnknownError(
                "Error: ${exception.message}"
            )
        }
    }
}

fun PasswordValidationStateDomain.toPasswordValidationUiState(): PasswordValidationUiState =
    when (this) {
        is PasswordValidationStateDomain.Empty -> PasswordValidationUiState.Empty
        is PasswordValidationStateDomain.Success -> PasswordValidationUiState.Success(validator)
        is PasswordValidationStateDomain.Error -> PasswordValidationUiState.Error(validator)
    }

fun PasswordConfirmationStateDomain.toPasswordConfirmationUiState(): PasswordConfirmationUiState =
    when (this) {
        is PasswordConfirmationStateDomain.Empty -> com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordConfirmationUiState.Empty
        is PasswordConfirmationStateDomain.Success -> com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordConfirmationUiState.Success
        is PasswordConfirmationStateDomain.Error -> com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordConfirmationUiState.Error
    }

fun ResultDomain<String>.toNicknameUiState(): NicknameUiState = when (this) {
    is ResultDomain.Success -> {
        when {
            data.isEmpty() -> NicknameUiState.Empty
            else -> NicknameUiState.Success
        }
    }
    is ResultDomain.Error -> {
        when (exception) {
            is NoInternetException -> NicknameUiState.NoInternetError
            is ValidationException -> NicknameUiState.Error((exception as ValidationException).uiText)
            is NetworkException -> {
                val networkException = exception as NetworkException
                when (networkException.code) {
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_400 -> NicknameUiState.Error(networkException.uiText)
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_500 -> NicknameUiState.ServerError
                    else -> NicknameUiState.UnknownError(
                        "Network error code: ${networkException.code}"
                    )
                }
            }
            else -> NicknameUiState.UnknownError(
                "Error: ${exception.message}"
            )
        }
    }
}

fun ResultDomain<String>.toFullNameUiState(): FullNameUiState = when (this) {
    is ResultDomain.Success -> {
        if (this.data.isEmpty())
            FullNameUiState.Empty
        else
            FullNameUiState.Success
    }

    is ResultDomain.Error -> {
        if (this.exception is ValidationException)
            FullNameUiState.Error((this.exception as ValidationException).uiText)
        else
            FullNameUiState.Error(
                com.glechyk.gram.delta.core.utils.UiText.DynamicString(
                    this.exception.message.orEmpty()
                )
            )
    }
}

fun ResultDomain<Unit>.toSendingVerificationCodeUiState(): SendingVerificationCodeUiState =
    when (this) {
        is ResultDomain.Success -> SendingVerificationCodeUiState.Success
        is ResultDomain.Error -> {
            when (exception) {
                is NoInternetException -> SendingVerificationCodeUiState.NoInternetError
                is RequestLimitException -> SendingVerificationCodeUiState.RequestLimitError(
                    (exception as RequestLimitException).tryAfterTime
                )
                is NetworkException -> {
                    val networkException = exception as NetworkException
                    when (networkException.code) {
                        com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_500 -> SendingVerificationCodeUiState.ServerError
                        else -> SendingVerificationCodeUiState.UnknownError(
                            "Network error code: ${networkException.code}"
                        )
                    }
                }
                else -> SendingVerificationCodeUiState.UnknownError(
                    "Error: ${exception.message}"
                )
            }
        }
    }

fun ResultDomain<Unit>.toConfirmationVerificationCodeUiState(): ConfirmationVerificationCodeUiState =
    when (this) {
        is ResultDomain.Success -> ConfirmationVerificationCodeUiState.Success
        is ResultDomain.Error -> {
            when (exception) {
                is NoInternetException -> ConfirmationVerificationCodeUiState.NoInternetError
                is RequestLimitException -> ConfirmationVerificationCodeUiState.RequestLimitError(
                    (exception as RequestLimitException).tryAfterTime
                )
                is NetworkException -> {
                    val networkException = exception as NetworkException
                    when (networkException.code) {
                        com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_500 -> ConfirmationVerificationCodeUiState.ServerError
                        else -> ConfirmationVerificationCodeUiState.UnknownError(
                            "Network error code: ${networkException.code}"
                        )
                    }
                }
                is ValidationException -> ConfirmationVerificationCodeUiState.Error((exception as ValidationException).uiText)
                else -> ConfirmationVerificationCodeUiState.UnknownError(
                    "Error: ${exception.message}"
                )
            }
        }
    }

fun ResultDomain<Unit>.toSignUpUiState(): SignUpUiState = when (this) {
    is ResultDomain.Success -> SignUpUiState.Success
    is ResultDomain.Error -> {
        when (exception) {
            is NoInternetException -> SignUpUiState.NoInternetError
            is NetworkException -> {
                val networkException = exception as NetworkException
                when (networkException.code) {
                    com.glechyk.gram.delta.core.utils.network.NetworkCodes.CODE_500 -> SignUpUiState.ServerError
                    else -> SignUpUiState.UnknownError(
                        "Network error code: ${networkException.code}"
                    )
                }
            }
            else -> SignUpUiState.UnknownError(
                "Error: ${exception.message}"
            )
        }
    }
}

fun TimerStateDomain.toTimerUiState(): TimerUiState = when (this) {
    is TimerStateDomain.Start -> com.glechyk.gram.delta.authFeature.impl.presentation.model.TimerUiState.Start(timeOfTimer)
    is TimerStateDomain.Tick -> com.glechyk.gram.delta.authFeature.impl.presentation.model.TimerUiState.Tick(millisUntilFinished)
    is TimerStateDomain.Finish -> com.glechyk.gram.delta.authFeature.impl.presentation.model.TimerUiState.Finish
}

fun UserCachedLogin.toTab(): Tab = when (this) {
    UserCachedLogin.Phone, UserCachedLogin.Nothing -> Tab.Phone
    UserCachedLogin.Email -> Tab.Email
}
