package com.andriikozakov.buttontoaction.network

import com.andriikozakov.buttontoaction.data.model.Action
import com.andriikozakov.buttontoaction.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface IConfigurationAPI {
    @GET(Constants.Network.URL_CONFIGURATION)
    suspend fun getConfiguration(): Response<List<Action>>

}