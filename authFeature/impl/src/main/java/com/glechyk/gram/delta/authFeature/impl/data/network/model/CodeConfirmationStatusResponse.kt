package com.glechyk.gram.delta.authFeature.impl.data.network.model


import com.google.gson.annotations.SerializedName

data class CodeConfirmationStatusResponse(
    @SerializedName("success")
    val success: Boolean?
)
