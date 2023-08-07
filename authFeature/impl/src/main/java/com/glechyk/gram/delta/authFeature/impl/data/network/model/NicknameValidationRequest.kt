package com.glechyk.gram.delta.authFeature.impl.data.network.model


import com.google.gson.annotations.SerializedName

data class NicknameValidationRequest(
    @SerializedName("nickName")
    val nickname: String
)
