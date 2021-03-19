package com.andriikozakov.buttontoaction.extensions

import android.net.ConnectivityManager
import com.andriikozakov.buttontoaction.data.model.base.BaseException
import com.andriikozakov.buttontoaction.data.model.base.BaseResponse
import com.andriikozakov.buttontoaction.data.model.base.BaseResult
import com.andriikozakov.buttontoaction.util.NetworkUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> getNetworkResult(
    call: suspend () -> Response<T>, connectivityManager: ConnectivityManager
): BaseResult<T> {
    if (NetworkUtils.isInternetAvailable(connectivityManager)) {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return BaseResult.success(body, response.message())
            }
            return error(NetworkUtils.mapWebServiceException(response))
        } catch (e: Exception) {
            return error(NetworkUtils.mapHttpConnectionExceptions(e))
        }
    } else {
        return error(NetworkUtils.mapDeviceOfflineException())
    }
}

private fun <T> error(exception: BaseException?): BaseResult<T> {
    val fullMessage = "${exception!!.errorTitle}\n${exception.errorMessage}"
    return BaseResult.error(fullMessage, null, exception.statusCode)
}

private fun convertErrorBody(throwable: HttpException): BaseResponse<Any>? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            Gson().fromJson(
                throwable.response()?.errorBody()?.charStream(),
                object : TypeToken<BaseResponse<Any>>() {}.type
            )
        }
    } catch (exception: Exception) {
        null
    }
}