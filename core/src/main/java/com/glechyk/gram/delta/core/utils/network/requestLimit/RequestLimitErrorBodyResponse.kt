package com.glechyk.gram.delta.core.utils.network.requestLimit


import com.google.gson.annotations.SerializedName

data class RequestLimitErrorBodyResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val message: MessageRequestLimit?,
    @SerializedName("statusCode")
    val statusCode: Int?
)
