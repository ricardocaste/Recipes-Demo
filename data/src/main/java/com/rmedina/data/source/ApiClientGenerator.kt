package com.rmedina.data.source

interface ApiClientGenerator {
    fun <T> generateApi(service: Class<T>): T
}