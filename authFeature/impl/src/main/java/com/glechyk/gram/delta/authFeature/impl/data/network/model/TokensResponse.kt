package com.glechyk.gram.delta.authFeature.impl.data.network.model


import com.google.gson.annotations.SerializedName

data class TokensResponse(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?
)
