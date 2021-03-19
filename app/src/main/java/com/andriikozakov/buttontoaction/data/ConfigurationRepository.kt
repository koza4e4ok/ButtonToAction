package com.andriikozakov.buttontoaction.data

import javax.inject.Inject

class ConfigurationRepository @Inject
constructor(val configurationDataSource: ConfigurationDataSource) : BaseRepository() {

    val getConfiguration =
        resultLiveData(networkCall = { configurationDataSource.getConfiguration() })

}