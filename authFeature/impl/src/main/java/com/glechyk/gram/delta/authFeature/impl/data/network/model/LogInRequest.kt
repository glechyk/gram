package com.glechyk.gram.delta.authFeature.impl.data.network.model


import com.google.gson.annotations.SerializedName

data class LogInRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)
