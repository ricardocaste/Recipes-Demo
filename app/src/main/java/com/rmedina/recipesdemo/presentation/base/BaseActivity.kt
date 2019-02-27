package com.rmedina.recipesdemo.presentation.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rmedina.recipesdemo.infrastructure.AndroidApplication
import com.rmedina.recipesdemo.infrastructure.di.component.ViewComponent
import com.rmedina.recipesdemo.infrastructure.di.module.ViewModule

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private val activityComponent by lazy {
        (application as AndroidApplication)
            .applicationComponent
            .viewComponent(ViewModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(activityComponent)
    }

    protected abstract fun initializeInjector(viewComponent: ViewComponent)

    override fun close() {
        finish()
    }

    override fun showMessage(message: String) {
        val view = findViewById<View>(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

}