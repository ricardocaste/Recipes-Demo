package com.rmedina.recipesdemo.infrastructure

import android.app.Application
import com.rmedina.recipesdemo.BuildConfig
import com.rmedina.recipesdemo.infrastructure.di.component.ApplicationComponent
import com.rmedina.recipesdemo.infrastructure.di.component.DaggerApplicationComponent
import com.rmedina.recipesdemo.infrastructure.di.module.ApplicationModule
import timber.log.Timber

class AndroidApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }


    override fun onCreate() {
        applicationComponent.inject(this)
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}