package com.glechyk.gram.delta.core.utils.network

enum class NetworkCodes(val code: Int) {
    CODE_400(400),
    CODE_401(401),
    CODE_403(403),
    CODE_404(404),
    CODE_409(409),
    CODE_429(429),
    CODE_500(500);

    override fun toString() = "$code"
}
