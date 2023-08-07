package com.glechyk.gram.delta.core.exception

class RequestLimitException(val tryAfterTime: Long) : Exception()
