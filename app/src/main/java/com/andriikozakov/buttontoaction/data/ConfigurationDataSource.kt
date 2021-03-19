package com.andriikozakov.buttontoaction.data

import android.net.ConnectivityManager
import com.andriikozakov.buttontoaction.extensions.getNetworkResult
import com.andriikozakov.buttontoaction.network.IConfigurationAPI
import javax.inject.Inject

class ConfigurationDataSource @Inject
constructor(
    private var configurationAPI: IConfigurationAPI,
    private var connectivityManager: ConnectivityManager
) {
    suspend fun getConfiguration() =
        getNetworkResult({ configurationAPI.getConfiguration() }, connectivityManager)
}