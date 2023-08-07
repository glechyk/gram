package com.glechyk.gram.delta.network.api

interface TokenManager {

    var accessToken: String?

    var refreshToken: String?

    fun deleteToken()

}
