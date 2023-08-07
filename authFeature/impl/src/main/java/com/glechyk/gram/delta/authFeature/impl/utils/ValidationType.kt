package com.glechyk.gram.delta.authFeature.impl.utils

sealed class ValidationType {

    data class Local(val value: String) : ValidationType()

    data class Network(val value: String) : ValidationType()

}
