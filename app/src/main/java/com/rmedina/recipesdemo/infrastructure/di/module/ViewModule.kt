package com.rmedina.recipesdemo.infrastructure.di.module

import android.app.Activity
import com.rmedina.recipesdemo.infrastructure.di.scope.ViewScope
import com.rmedina.recipesdemo.presentation.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ViewModule(private val activity: BaseActivity) {

    @Provides
    @ViewScope
    internal fun activity(): Activity {
        return activity
    }

}