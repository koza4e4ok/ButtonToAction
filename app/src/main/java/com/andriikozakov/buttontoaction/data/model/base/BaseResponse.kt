package com.andriikozakov.buttontoaction.data.model.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse<T>(
    @SerializedName("data") var data: T? = null,
    @SerializedName("error") var error: Boolean,
    @SerializedName("message") var message: String? = ""
) : Serializable
