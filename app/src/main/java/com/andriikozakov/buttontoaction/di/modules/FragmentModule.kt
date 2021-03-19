package com.andriikozakov.buttontoaction.di.modules

import com.andriikozakov.buttontoaction.ui.main.buttontoaction.ButtonToActionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindButtonToActionFragment(): ButtonToActionFragment

}