package com.rmedina.recipesdemo.presentation.base

abstract class BasePresenter<T : BaseView> {
    lateinit var view: T

    open fun onAttach(view: T) {
        this.view = view
    }
}