package com.rmedina.recipesdemo.data.repository

import com.rmedina.recipesdemo.domain.exception.ConnectionTimeoutException
import com.rmedina.recipesdemo.domain.exception.NoConnectionException
import com.rmedina.recipesdemo.domain.exception.RepositoryException
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRepository {

    protected fun <T> executeCall(call: Call<T>): T {
        try {
            val response = call.execute()
            if (!response.isSuccessful) {
                handleHttpError(response)
            }
            return response.body() ?: throw RepositoryException("Body response is null")
        } catch (e: IOException) {
            if (e is SocketTimeoutException) {
                throw ConnectionTimeoutException()
            } else {
                throw NoConnectionException()
            }
        }
    }

    protected fun <T> executeEmptyCall(call: Call<T>) {
        try {
            val response = call.execute()
            if (!response.isSuccessful) {
                handleHttpError(response)
            }
        } catch (e: IOException) {
            if (e is SocketTimeoutException) {
                throw ConnectionTimeoutException()
            } else {
                throw NoConnectionException()
            }
        }
    }

    @Throws(IOException::class)
    private fun <T> handleHttpError(response: Response<T>) {
        throw HttpException(response)
    }


}