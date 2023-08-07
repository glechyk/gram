package com.glechyk.gram.delta.core.exception

import com.glechyk.gram.delta.core.utils.Constants
import com.glechyk.gram.delta.core.utils.UiText
import com.glechyk.gram.delta.core.utils.network.NetworkCodes

class NetworkException(
    val code: NetworkCodes,
    val uiText: UiText = UiText.DynamicString(Constants.EMPTY_STRING),
) : Exception()
