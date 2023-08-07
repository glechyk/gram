package com.glechyk.gram.delta.authFeature.impl.data.network.model


import com.google.gson.annotations.SerializedName

data class LogInFacebookRequest(
    @SerializedName("userToken")
    val userToken: String
)
