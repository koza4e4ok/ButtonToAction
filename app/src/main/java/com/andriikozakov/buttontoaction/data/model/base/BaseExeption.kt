package com.andriikozakov.buttontoaction.data.model.base

class BaseException(
    private var errorCode: String? = null,
    private var throwable: Throwable? = null,
    var errorTitle: String? = null,
    var errorMessage: String? = null,
    var statusCode: Int? = null
) : RuntimeException()