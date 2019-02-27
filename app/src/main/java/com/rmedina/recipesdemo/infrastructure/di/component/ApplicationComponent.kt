package com.rmedina.recipesdemo.infrastructure.di.component

import com.rmedina.recipesdemo.infrastructure.AndroidApplication
import com.rmedina.recipesdemo.infrastructure.di.module.ApplicationModule
import com.rmedina.recipesdemo.infrastructure.di.module.ViewModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(application: AndroidApplication)

    fun viewComponent(viewModule: ViewModule): ViewComponent
}