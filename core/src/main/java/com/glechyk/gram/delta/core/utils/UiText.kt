package com.glechyk.gram.delta.core.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {

    data class DynamicString(var value: String) : UiText()

    class StringResource(@StringRes val resId: Int) : UiText()

}

fun UiText.asString(context: Context) = when (this) {
    is UiText.DynamicString -> this.value
    is UiText.StringResource -> context.getString(this.resId)
}
