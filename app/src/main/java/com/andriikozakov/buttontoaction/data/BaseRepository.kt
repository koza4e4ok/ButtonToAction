package com.andriikozakov.buttontoaction.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.andriikozakov.buttontoaction.data.model.base.BaseResult
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository {

    protected fun <T> resultLiveData(networkCall: suspend () -> BaseResult<T>): LiveData<BaseResult<T>> =
        liveData(Dispatchers.IO) {
            emit(BaseResult.loading())
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == BaseResult.Status.SUCCESS) {
                emit(BaseResult.success(responseStatus.data, responseStatus.message))
            } else if (responseStatus.status == BaseResult.Status.ERROR)
                emit(BaseResult.error(responseStatus.message!!, null, responseStatus.statusCode))
            else if (responseStatus.status == BaseResult.Status.NETWORK_ERROR)
                emit(BaseResult.networkError(responseStatus.toString()))

        }
}