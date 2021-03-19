package com.andriikozakov.buttontoaction.di

import android.app.Application
import com.andriikozakov.buttontoaction.ButtonToActionApplication
import com.andriikozakov.buttontoaction.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        NetworkModule::class,
        FrameworkModule::class,
        StorageModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        NotificationModule::class]
)
interface IMainComponent : AndroidInjector<ButtonToActionApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): IMainComponent


    }
}