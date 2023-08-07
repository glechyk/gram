package com.glechyk.gram.delta.authFeature.impl.domain.repository

import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.authFeature.impl.utils.UserCachedLogin
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val emailFlow: Flow<String>

    val phoneNumberFlow: Flow<String>

    val passwordFlow: Flow<String>

    val nicknameFlow: Flow<String>

    val fullNameFlow: Flow<String>

    val birthdayFlow: Flow<Long>

    suspend fun cacheEmail(email: String?)

    suspend fun cachePhoneNumber(phoneNumber: String?)

    suspend fun cachePassword(password: String?)

    suspend fun cacheNickname(nickname: String?)

    suspend fun cacheFullName(fullName: String?)

    suspend fun cacheBirthday(date: Long?)

    suspend fun resetAllCache()

    suspend fun logIn(username: String, password: String): ResultDomain<Unit>

    suspend fun logInGoogle(
        gmail: String,
        googleId: String,
        fullName: String,
        dateOfBirthday: Long
    ): ResultDomain<Unit>

    suspend fun logInFacebook(token: String): ResultDomain<Unit>

    suspend fun signUp(): ResultDomain<Unit>

    suspend fun sendVerificationCode(): ResultDomain<Unit>

    suspend fun confirmCode(code: String): ResultDomain<Unit>

    fun getUserCachedLogin(): UserCachedLogin

}
