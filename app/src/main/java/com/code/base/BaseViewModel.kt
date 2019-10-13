package com.code.base

import androidx.lifecycle.ViewModel
import com.code.details.DetailsViewModel
import com.code.injection.component.DaggerViewModelInjector
import com.code.injection.component.ViewModelInjector
import com.code.injection.module.NetworkModule
import com.code.home.HomeViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .networkModule(NetworkModule).build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is HomeViewModel -> injector.inject(this)
            is DetailsViewModel -> injector.inject(this)
        }
    }
}