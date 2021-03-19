package com.andriikozakov.buttontoaction.di.modules

import com.andriikozakov.buttontoaction.data.ConfigurationDataSource
import com.andriikozakov.buttontoaction.data.ConfigurationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideConfigurationRepository(configurationDataSource: ConfigurationDataSource): ConfigurationRepository {
        return ConfigurationRepository(configurationDataSource)
    }

}