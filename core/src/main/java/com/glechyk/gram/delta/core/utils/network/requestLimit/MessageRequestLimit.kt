package com.glechyk.gram.delta.core.utils.network.requestLimit


import com.google.gson.annotations.SerializedName

data class MessageRequestLimit(
    @SerializedName("message")
    val message: String?,
    @SerializedName("tryAfter")
    val tryAfter: Long?
)
