package com.glechyk.gram.delta.network.api

interface Network {

    fun <T> getService(clazz: Class<T>, baseUrl: String): T

}
