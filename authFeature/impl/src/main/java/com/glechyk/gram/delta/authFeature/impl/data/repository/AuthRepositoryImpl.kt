package com.glechyk.gram.delta.authFeature.impl.data.repository

import android.icu.text.SimpleDateFormat
import com.chi.interngram.delta.authFeature.impl.R
import com.glechyk.gram.delta.authFeature.impl.data.network.api.AuthApi
import com.glechyk.gram.delta.authFeature.impl.data.network.model.LogInGoogleRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.LogInFacebookRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.SendVerificationCodeToEmailRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.SignUpRequest
import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.authFeature.impl.data.network.model.LogInRequest
import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.utils.LoginExceptionType
import com.glechyk.gram.delta.authFeature.impl.utils.UserCachedLogin
import com.glechyk.gram.delta.authFeature.impl.utils.ValidationException
import com.glechyk.gram.delta.core.utils.UiText
import com.glechyk.gram.delta.core.utils.network.toResultDomain
import com.glechyk.gram.delta.authFeature.impl.utils.isPhoneNumber
import com.glechyk.gram.delta.network.api.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import java.util.Locale

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {

    private val emailStateFlow = MutableStateFlow<String?>(null)
    override val emailFlow: Flow<String> = emailStateFlow.filterNotNull()

    private val phoneNumberStateFlow = MutableStateFlow<String?>(null)
    override val phoneNumberFlow: Flow<String> = phoneNumberStateFlow.filterNotNull()

    private val passwordStateFlow = MutableStateFlow<String?>(null)
    override val passwordFlow: Flow<String> = passwordStateFlow.filterNotNull()

    private val nicknameStateFlow = MutableStateFlow<String?>(null)
    override val nicknameFlow: Flow<String> = nicknameStateFlow.filterNotNull()

    private val fullNameStateFlow = MutableStateFlow<String?>(null)
    override val fullNameFlow: Flow<String> = fullNameStateFlow.filterNotNull()

    private val birthdayStateFlow = MutableStateFlow<Long?>(null)
    override val birthdayFlow: Flow<Long> = birthdayStateFlow.filterNotNull()

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z", Locale.ENGLISH)

    override suspend fun cacheEmail(email: String?) {
        emailStateFlow.emit(email)
    }

    override suspend fun cachePhoneNumber(phoneNumber: String?) {
        phoneNumberStateFlow.emit(phoneNumber)
    }

    override suspend fun cachePassword(password: String?) {
        passwordStateFlow.emit(password)
    }

    override suspend fun cacheNickname(nickname: String?) {
        nicknameStateFlow.emit(nickname)
    }

    override suspend fun cacheFullName(fullName: String?) {
        fullNameStateFlow.emit(fullName)
    }

    override suspend fun cacheBirthday(date: Long?) {
        birthdayStateFlow.emit(date)
    }

    override suspend fun resetAllCache() {
        emailStateFlow.emit(null)
        phoneNumberStateFlow.emit(null)
        passwordStateFlow.emit(null)
        nicknameStateFlow.emit(null)
        fullNameStateFlow.emit(null)
        birthdayStateFlow.emit(null)
    }

    override suspend fun logIn(username: String, password: String): ResultDomain<Unit> =
        runCatching {
            val response = authApi.logIn(
                LogInRequest(
                    username = if (username.takeLast(9).isPhoneNumber()) username.takeLast(9)
                    else username,
                    password = password
                )
            )
            response.toResultDomain(
                onSuccess = {
                    response.body()?.let {
                        tokenManager.accessToken = it.accessToken
                        tokenManager.refreshToken = it.refreshToken
                    }
                    ResultDomain.Success(Unit)
                },
                error400UiText = LoginExceptionType.InvalidInput.uiText,
                error401UiText = LoginExceptionType.IncorrectInput.uiText
            )
        }.getOrElse {
            ResultDomain.Error(it)
        }

    override suspend fun logInGoogle(
        gmail: String,
        googleId: String,
        fullName: String,
        dateOfBirthday: Long
    ): ResultDomain<Unit> = runCatching {
        val response = authApi.logInGoogle(
            LogInGoogleRequest(
                email = gmail,
                googleId = googleId,
                fullName = fullName,
                dateOfBirth = dateFormatter.format(dateOfBirthday)
            )
        )
        response.toResultDomain(
            onSuccess = {
                response.body()?.let {
                    tokenManager.accessToken = it.accessToken
                    tokenManager.refreshToken = it.refreshToken
                }
                ResultDomain.Success(Unit)
            },
            error400UiText = UiText.StringResource(com.chi.interngram.delta.core.R.string.server_error)
        )
    }.getOrElse {
        ResultDomain.Error(it)
    }

    override suspend fun logInFacebook(token: String): ResultDomain<Unit> =
        runCatching {
            val response = authApi.logInFacebook(LogInFacebookRequest(token))
            response.toResultDomain(
                onSuccess = {
                    response.body()?.let {
                        tokenManager.accessToken = it.accessToken
                        tokenManager.refreshToken = it.refreshToken
                    }
                    ResultDomain.Success(Unit)
                },
                error400UiText = UiText.StringResource(com.chi.interngram.delta.core.R.string.server_error),
                error401UiText = UiText.StringResource(com.chi.interngram.delta.core.R.string.server_error)
            )
        }.getOrElse {
            ResultDomain.Error(it)
        }

    override suspend fun signUp(): ResultDomain<Unit> = runCatching {
        val response = authApi.signUp(
            SignUpRequest(
                email = emailStateFlow.value,
                phoneNumber = phoneNumberStateFlow.value,
                password = passwordStateFlow.value ?: "",
                nickName = nicknameStateFlow.value ?: "",
                fullName = fullNameStateFlow.value ?: "",
                dateOfBirth = dateFormatter.format(birthdayStateFlow.value ?: 0)
            )
        )
        response.toResultDomain(
            onSuccess = {
                response.body()?.let {
                    tokenManager.accessToken = it.accessToken
                    tokenManager.refreshToken = it.refreshToken
                }
                ResultDomain.Success(Unit)
            }
        )
    }.getOrElse {
        ResultDomain.Error(it)
    }

    override suspend fun sendVerificationCode(): ResultDomain<Unit> = runCatching {
        val response = when {
            phoneNumberStateFlow.value != null -> authApi.sendVerificationCodeToPhone(
                phoneNumberStateFlow.value ?: ""
            )
            else -> authApi.sendVerificationCodeToEmail(
                SendVerificationCodeToEmailRequest(emailStateFlow.value ?: "")
            )
        }
        response.toResultDomain(
            onSuccess = {
                ResultDomain.Success(Unit)
            }
        )
    }.getOrElse {
        ResultDomain.Error(it)
    }

    override suspend fun confirmCode(code: String): ResultDomain<Unit> = runCatching {
        val response = when {
            phoneNumberStateFlow.value != null -> authApi.confirmVerificationCodeFromPhone(
                phoneNumber = phoneNumberStateFlow.value ?: "",
                code = code
            )
            else -> authApi.confirmVerificationCodeFromEmail(
                email = emailStateFlow.value ?: "",
                code = code
            )
        }
        response.toResultDomain(
            onSuccess = {
                val body = response.body()
                if (body != null && body.success == true) {
                    ResultDomain.Success(Unit)
                } else {
                    ResultDomain.Error(
                        ValidationException(
                            UiText.StringResource(
                                R.string.validation_error_verification_code
                            )
                        )
                    )
                }
            }
        )
    }.getOrElse {
        ResultDomain.Error(it)
    }

    override fun getUserCachedLogin(): UserCachedLogin {
        return when {
            phoneNumberStateFlow.value != null -> UserCachedLogin.Phone
            emailStateFlow.value != null -> UserCachedLogin.Email
            else -> UserCachedLogin.Nothing
        }
    }
}
