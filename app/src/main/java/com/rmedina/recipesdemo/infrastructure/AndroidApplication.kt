package com.rmedina.recipesdemo.infrastructure

import android.app.Application
import com.rmedina.recipesdemo.infrastructure.di.apiClientGeneratorModule
import com.rmedina.recipesdemo.infrastructure.di.presenterModule
import com.rmedina.recipesdemo.infrastructure.di.repositoryModule
import com.rmedina.recipesdemo.infrastructure.di.useCaseModule
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        insertKoin()
    }

    private fun insertKoin() {
        startKoin {
            modules(listOf(repositoryModule, useCaseModule, presenterModule, apiClientGeneratorModule))
        }
    }

}