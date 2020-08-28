package com.loblaw.redditnews.data.remote.network.exception

import com.loblaw.redditnews.base.domain.exception.LobLawNewsException
import retrofit2.Response
import java.io.IOException

object NetworkException {
    fun httpError(response: Response<Any>?): LobLawNewsException {
        val message: String? = null
        var responseBody = ""
        var statusCode = 0
        val errorCode = 0
        response?.let { statusCode = it.code() }
        response?.let {
            responseBody = it.errorBody()!!.string()
            try {
                // in case of handle http API error
            } catch (exception: Exception) {
            }
        }

        var kind = LobLawNewsException.Kind.HTTP
        when (statusCode) {
            500 -> kind = LobLawNewsException.Kind.SERVER_DOWN
            408 -> kind = LobLawNewsException.Kind.TIME_OUT
            401 -> kind = LobLawNewsException.Kind.UNAUTHORIZED
        }

        return LobLawNewsException(kind, message?.let { message }
            ?: run { "" })
            .setErrorCode(errorCode)
            .setStatusCode(statusCode)
            .setData(responseBody)
    }

    fun networkError(exception: IOException): LobLawNewsException {
        return LobLawNewsException(LobLawNewsException.Kind.NETWORK, exception)
    }

    fun unexpectedError(exception: Throwable): LobLawNewsException {
        return LobLawNewsException(LobLawNewsException.Kind.UNEXPECTED, exception)
    }
}