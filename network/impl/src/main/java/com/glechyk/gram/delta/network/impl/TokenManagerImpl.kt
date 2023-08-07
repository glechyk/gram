package com.glechyk.gram.delta.network.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.glechyk.gram.delta.network.api.TokenManager

class TokenManagerImpl(
    private val sharedPreferences: SharedPreferences
): TokenManager {

    override var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        set(value) {
            sharedPreferences.edit(commit = true) {
                putString(ACCESS_TOKEN_KEY, value)
            }
        }

    override var refreshToken: String?
        get() = sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
        set(value) {
            sharedPreferences.edit(commit = true) {
                putString(REFRESH_TOKEN_KEY, value)
            }
        }

    override fun deleteToken() {
        accessToken = null
        refreshToken = null
    }

    private companion object {
        const val ACCESS_TOKEN_KEY = "access_token"
        const val REFRESH_TOKEN_KEY = "refresh_token"
    }

}
