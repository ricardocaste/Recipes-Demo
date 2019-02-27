package com.rmedina.recipesdemo.infrastructure.di.component

import com.rmedina.recipesdemo.presentation.main.activities.MainActivity
import com.rmedina.recipesdemo.infrastructure.di.module.ViewModule
import com.rmedina.recipesdemo.infrastructure.di.scope.ViewScope
import dagger.Subcomponent

@ViewScope
@Subcomponent(
    modules = [
        ViewModule::class
    ]
)
interface ViewComponent {

    fun inject(activity: MainActivity)
}