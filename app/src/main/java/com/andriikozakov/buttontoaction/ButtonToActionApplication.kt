package com.andriikozakov.buttontoaction

import android.app.Application
import com.andriikozakov.buttontoaction.di.DaggerIMainComponent
import com.andriikozakov.buttontoaction.util.SharedPreferenceHelper
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

val preferenceHelper: SharedPreferenceHelper by lazy {
    ButtonToActionApplication.preferenceHelper!!
}

class ButtonToActionApplication : Application(), HasAndroidInjector {
    companion object {
        var preferenceHelper: SharedPreferenceHelper? = null
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        DaggerIMainComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
        preferenceHelper = SharedPreferenceHelper(applicationContext)
        super.onCreate()
    }

    override fun androidInjector() = androidInjector

}