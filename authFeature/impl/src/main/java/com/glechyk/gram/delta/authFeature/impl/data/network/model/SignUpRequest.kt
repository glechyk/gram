package com.glechyk.gram.delta.authFeature.impl.data.network.model


import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("email")
    val email: String?,
    @SerializedName("nickName")
    val nickName: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String?
)
