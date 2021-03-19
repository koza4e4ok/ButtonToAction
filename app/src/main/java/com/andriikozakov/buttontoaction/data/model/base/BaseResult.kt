package com.andriikozakov.buttontoaction.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseResult<out T>(
    val status: Status,
    @SerializedName("data") val data: T?,
    @SerializedName("error") val message: String?,
    val statusCode: Int? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        NETWORK_ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?, message: String?): BaseResult<T> {
            return BaseResult(Status.SUCCESS, data, message)
        }

        fun <T> error(message: String, data: T? = null, statusCode: Int?): BaseResult<T> {
            return BaseResult(Status.ERROR, data, message, statusCode)
        }

        fun <T> networkError(message: String, data: T? = null): BaseResult<T> {
            return BaseResult(Status.NETWORK_ERROR, data, message)
        }

        fun <T> loading(data: T? = null): BaseResult<T> {
            return BaseResult(Status.LOADING, data, null)
        }
    }
}