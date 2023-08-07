package com.glechyk.gram.delta.network.impl

import com.glechyk.gram.delta.core.exception.NoInternetException
import com.glechyk.gram.delta.network.api.Network
import com.glechyk.gram.delta.network.api.TokenManager
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkFactory(
    private val tokenManager: TokenManager,
) : Network {

    private val gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val httpClient by lazy {

        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val accessToken = tokenManager.accessToken
            val request = if (accessToken != null) {
                chain.request().newBuilder()
                    .addHeader("Authorization", accessToken)
                    .build()
            } else {
                chain.request()
            }
            runCatching {
                chain.proceed(
                    request
                )
            }.getOrElse { throwable ->
                if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
                    throw NoInternetException()
                }
                throw throwable
            }
        }

        tokenManager.refreshToken?.let { refreshToken ->
            builder.authenticator { _, response ->
                val uploadedToken =
                    refreshToken// serviceApi.getNewToken(refreshToken) there will be request for new token
                response.request.newBuilder()
                    .header("Authorization", uploadedToken)
                    .build()
            }
        }

        builder.build()
    }

    override fun <T> getService(clazz: Class<T>, baseUrl: String): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()
        .create(clazz)

}
