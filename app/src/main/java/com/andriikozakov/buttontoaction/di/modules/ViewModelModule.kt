package com.andriikozakov.buttontoaction.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriikozakov.buttontoaction.di.ViewModelFactory
import com.andriikozakov.buttontoaction.ui.main.buttontoaction.ButtonToActionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ButtonToActionViewModel::class)
    internal abstract fun bindButtonToActionViewModel(buttonToActionViewModel: ButtonToActionViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}