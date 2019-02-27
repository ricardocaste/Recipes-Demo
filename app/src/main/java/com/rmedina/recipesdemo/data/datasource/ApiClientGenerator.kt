package com.rmedina.recipesdemo.data.datasource

interface ApiClientGenerator {
    fun <T> generateApi(service: Class<T>): T
}