package com.rmedina.recipesdemo.data.datasource

import com.google.gson.GsonBuilder
import com.rmedina.recipesdemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitApiClientGenerator @Inject constructor() : ApiClientGenerator {

    private val retrofit: Retrofit

    init {
        val builder = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(jsonConverterFactory())
            .client(buildHttpClient())

        retrofit = builder.build()
    }

    private fun jsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        return GsonConverterFactory.create(gson)
    }

    private fun buildHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

    private fun httpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    override fun <T> generateApi(service: Class<T>): T {
        return retrofit.create(service)
    }
}
