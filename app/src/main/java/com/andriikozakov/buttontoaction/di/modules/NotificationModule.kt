package com.andriikozakov.buttontoaction.di.modules

import android.app.Application
import com.andriikozakov.buttontoaction.util.NotificationCenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationCenter(application: Application): NotificationCenter {
        return NotificationCenter(application)
    }
}