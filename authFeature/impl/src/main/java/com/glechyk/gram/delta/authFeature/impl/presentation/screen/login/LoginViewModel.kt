package com.glechyk.gram.delta.authFeature.impl.presentation.screen.login

import android.accounts.Account
import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginGoogleUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginUseCase
import com.glechyk.gram.delta.authFeature.impl.presentation.mappers.toLoginUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.LoginUiState
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.people.v1.PeopleService
import com.google.api.services.people.v1.PeopleServiceScopes
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import androidx.lifecycle.ViewModel
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginFacebookUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
    private val loginFacebookUseCase: LoginFacebookUseCase
) : ViewModel() {

    private val _uiState = MutableSharedFlow<LoginUiState>()
    val uiState: SharedFlow<LoginUiState> = _uiState

    fun logIn(username: String, password: String) = viewModelScope.launch {
        val result = loginUseCase.execute(username, password)
        _uiState.emit(result.toLoginUiState())
    }

    fun logInGoogle(
        context: Context,
        googleIdTokenVerifier: GoogleIdTokenVerifier,
        googleIdTokenString: String
    ) = viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException, is SocketTimeoutException -> emitUiState(LoginUiState.NoInternetError)
            else -> emitUiState(
                LoginUiState.UnknownError(
                    "Error: ${throwable.message}"
                )
            )
        }
    }) {
        val googleIdToken = googleIdTokenVerifier.verify(googleIdTokenString)
        val payload = googleIdToken.payload

        val googleCredential = GoogleAccountCredential.usingOAuth2(
            context,
            setOf(PeopleServiceScopes.USER_BIRTHDAY_READ, PeopleServiceScopes.USERINFO_PROFILE)
        )
            .setSelectedAccount(
                Account(
                    payload.email,
                    GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE
                )
            )

        val service = PeopleService.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            googleCredential
        ).build()
        val profile =
            service.people().get(PEOPLE_API_ME_REQUEST)
                .setPersonFields(PEOPLE_API_SCOPES)
                .execute()

        val dateFromProfile = profile.birthdays[0].date
        val nameFromProfile = profile.names[0].displayName

        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar[Calendar.DAY_OF_MONTH] = dateFromProfile.day
        calendar[Calendar.MONTH] = dateFromProfile.month - 1
        calendar[Calendar.YEAR] = dateFromProfile.year

        val dateOfBirthday = calendar.timeInMillis

        val result = loginGoogleUseCase.execute(
            gmail = payload.email,
            googleId = payload.subject,
            fullName = nameFromProfile,
            dateOfBirthday = dateOfBirthday
        )
        _uiState.emit(result.toLoginUiState())
    }

    fun logInFacebook(token: String) = viewModelScope.launch {
        _uiState.emit(loginFacebookUseCase.execute(token).toLoginUiState())
    }

    fun emitUiState(uiState: LoginUiState) = viewModelScope.launch {
        _uiState.emit(uiState)

    }

    private companion object {
        const val PEOPLE_API_ME_REQUEST = "people/me"
        const val PEOPLE_API_SCOPES = "birthdays,names"
    }
}
