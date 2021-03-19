package com.andriikozakov.buttontoaction.di.modules

import android.net.ConnectivityManager
import com.andriikozakov.buttontoaction.data.ConfigurationDataSource
import com.andriikozakov.buttontoaction.network.IConfigurationAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideConfigurationDataSource(
        iConfigurationAPI: IConfigurationAPI,
        connectivityManager: ConnectivityManager
    ): ConfigurationDataSource {
        return ConfigurationDataSource(iConfigurationAPI, connectivityManager)
    }

}