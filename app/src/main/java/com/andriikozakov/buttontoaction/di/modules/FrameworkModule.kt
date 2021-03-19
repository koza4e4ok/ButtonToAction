package com.andriikozakov.buttontoaction.di.modules

import android.app.Application
import android.content.Context
import com.andriikozakov.buttontoaction.util.Constants.Companion.DATE_FORMAT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.lang.reflect.Modifier
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class FrameworkModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithModifiers(
            Modifier.FINAL,
            Modifier.TRANSIENT,
            Modifier.STATIC
        ).setPrettyPrinting()
            .setDateFormat(DATE_FORMAT)
            .serializeNulls()
            .create()
    }
}