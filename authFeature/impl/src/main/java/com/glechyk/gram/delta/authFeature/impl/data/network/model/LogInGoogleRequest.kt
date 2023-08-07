package com.glechyk.gram.delta.authFeature.impl.data.network.model

import com.google.gson.annotations.SerializedName

data class LogInGoogleRequest(
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("googleId")
    val googleId: String?
)
