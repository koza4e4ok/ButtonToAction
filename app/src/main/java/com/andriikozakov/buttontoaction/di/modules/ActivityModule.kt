package com.andriikozakov.buttontoaction.di.modules

import com.andriikozakov.buttontoaction.ui.MainActivity
import com.andriikozakov.buttontoaction.util.NotificationCenter
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeNotificationCenter(): NotificationCenter
}