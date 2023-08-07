package com.glechyk.gram.delta.core.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/** Hide keyboard */
fun hideKeyboard(activity: Activity) {
    val view = activity.currentFocus
    if (view != null) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
