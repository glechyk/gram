package com.glechyk.gram.delta.core.utils.network

import com.chi.interngram.delta.core.R
import com.glechyk.gram.delta.core.exception.NetworkException
import com.glechyk.gram.delta.core.exception.RequestLimitException
import com.glechyk.gram.delta.core.utils.Constants
import com.glechyk.gram.delta.core.utils.ResultDomain
import com.glechyk.gram.delta.core.utils.UiText
import com.glechyk.gram.delta.core.utils.network.requestLimit.RequestLimitErrorBodyResponse
import com.google.gson.Gson
import retrofit2.Response

fun <T> Response<*>.toResultDomain(
    onSuccess: (() -> ResultDomain<T>),
    error400UiText: UiText? = null,
    error401UiText: UiText? = null,
    error403UiText: UiText? = null,
    error404UiText: UiText? = null,
    error409UiText: UiText? = null
): ResultDomain<T> = if (isSuccessful) {
    onSuccess.invoke()
} else {
    when (code()) {
        NetworkCodes.CODE_400.code -> {
            ResultDomain.Error(
                NetworkException(
                    code = NetworkCodes.CODE_400,
                    uiText = error400UiText ?: UiText.DynamicString(Constants.EMPTY_STRING),
                )
            )
        }
        NetworkCodes.CODE_401.code -> {
            ResultDomain.Error(
                NetworkException(
                    code = NetworkCodes.CODE_401,
                    uiText = error401UiText ?: UiText.DynamicString(Constants.EMPTY_STRING),
                )
            )
        }
        NetworkCodes.CODE_403.code -> {
            ResultDomain.Error(
                NetworkException(
                    code = NetworkCodes.CODE_403,
                    uiText = error403UiText ?: UiText.DynamicString(Constants.EMPTY_STRING),
                )
            )
        }
        NetworkCodes.CODE_404.code -> {
            ResultDomain.Error(
                NetworkException(
                    code = NetworkCodes.CODE_404,
                    uiText = error404UiText ?: UiText.DynamicString(Constants.EMPTY_STRING),
                )
            )
        }
        NetworkCodes.CODE_409.code -> {
            ResultDomain.Error(
                NetworkException(
                    code = NetworkCodes.CODE_409,
                    uiText = error409UiText ?: UiText.DynamicString(Constants.EMPTY_STRING),
                )
            )
        }
        NetworkCodes.CODE_429.code -> {
            val errorBody = errorBody()
            when {
                errorBody != null -> {
                    val gson = Gson()
                    val requestLimitErrorBodyResponse = gson.fromJson(
                        errorBody.string(),
                        RequestLimitErrorBodyResponse::class.java
                    )
                    ResultDomain.Error(
                        RequestLimitException(
                            requestLimitErrorBodyResponse.message?.tryAfter ?: 0
                        )
                    )
                }
                else -> ResultDomain.Error(
                    NetworkException(
                        code = NetworkCodes.CODE_429,
                    )
                )
            }
        }
        NetworkCodes.CODE_500.code -> {
            ResultDomain.Error(
                NetworkException(
                    code = NetworkCodes.CODE_500,
                    uiText = UiText.StringResource(R.string.server_error),
                )
            )
        }
        else -> throw IllegalStateException("Unknown Exception")
    }
}
