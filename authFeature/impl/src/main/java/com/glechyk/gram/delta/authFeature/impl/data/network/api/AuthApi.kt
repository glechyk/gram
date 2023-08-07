package com.glechyk.gram.delta.authFeature.impl.data.network.api

import com.glechyk.gram.delta.authFeature.impl.data.network.model.CodeConfirmationStatusResponse
import com.glechyk.gram.delta.authFeature.impl.data.network.model.LogInRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.TokensResponse
import com.glechyk.gram.delta.authFeature.impl.data.network.model.EmailValidationResponse
import com.glechyk.gram.delta.authFeature.impl.data.network.model.LogInFacebookRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.LogInGoogleRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.NicknameValidationRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.SignUpRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.SendVerificationCodeToEmailRequest
import com.glechyk.gram.delta.authFeature.impl.data.network.model.PhoneNumberValidationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @POST("auth/sign-in")
    suspend fun logIn(
        @Body logInRequest: LogInRequest
    ): Response<TokensResponse>

    @POST("auth/google")
    suspend fun logInGoogle(
        @Body logInGoogleRequest: LogInGoogleRequest
    ): Response<TokensResponse>

    @POST("auth/sign-in/facebook")
    suspend fun logInFacebook(
        @Body logInFacebookRequest: LogInFacebookRequest
    ): Response<TokensResponse>

    @POST("auth/sign-up")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<TokensResponse>

    @POST("auth/nickname")
    suspend fun validateNicknameForUniqueness(
        @Body nicknameValidationRequest: NicknameValidationRequest
    ): Response<Unit>

    @POST("auth/phone-verification/{phoneNumber}")
    suspend fun sendVerificationCodeToPhone(
        @Path("phoneNumber") phoneNumber: String
    ): Response<Unit>

    @POST("auth/email-send-code")
    suspend fun sendVerificationCodeToEmail(
        @Body sendVerificationCodeToEmailRequest: SendVerificationCodeToEmailRequest
    ): Response<Unit>

    @GET("auth/phone-confirmation/{phoneNumber}/{code}")
    suspend fun confirmVerificationCodeFromPhone(
        @Path("phoneNumber") phoneNumber: String,
        @Path("code") code: String
    ): Response<CodeConfirmationStatusResponse>

    @GET("auth/email-code-confirmation/{email}/{code}")
    suspend fun confirmVerificationCodeFromEmail(
        @Path("email") email: String,
        @Path("code") code: String
    ): Response<CodeConfirmationStatusResponse>

    @GET("auth/phone-validation/{phoneNumber}")
    suspend fun validatePhoneNumber(
        @Path("phoneNumber") phoneNumber: String
    ): Response<PhoneNumberValidationResponse>

    @GET("auth/email-verification/{email}")
    suspend fun validateEmail(
        @Path("email") email: String
    ): Response<EmailValidationResponse>
}
