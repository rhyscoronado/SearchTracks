package com.code.injection.component

import com.code.details.DetailsViewModel
import com.code.injection.module.NetworkModule
import com.code.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

  fun inject(homeViewModel: HomeViewModel)
  fun inject(detailsViewModel: DetailsViewModel)

  @Component.Builder
  interface Builder {
    fun build(): ViewModelInjector

    fun networkModule(networkModule: NetworkModule): Builder
  }
}