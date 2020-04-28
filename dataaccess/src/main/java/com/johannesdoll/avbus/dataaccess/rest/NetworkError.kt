package com.johannesdoll.avbus.dataaccess.rest

import com.johannesdoll.avbus.dataaccess.rest.NetworkError.HttpError
import com.johannesdoll.avbus.dataaccess.rest.NetworkError.HttpError.*
import com.johannesdoll.avbus.dataaccess.rest.NetworkError.Unknown
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

internal sealed class NetworkError {
    sealed class HttpError : NetworkError() {
        abstract val statusCode: Int

        // 400
        object BadRequst : HttpError() {
            override val statusCode =
                StatusCode.BAD_REQUEST
        }

        object Unauthorized : HttpError() {
            override val statusCode =
                StatusCode.UNAUTHORIZED
        }

        object NotFound : HttpError() {
            override val statusCode =
                StatusCode.NOT_FOUND
        }

        // 500
        object InternalServerError : HttpError() {
            override val statusCode =
                StatusCode.INTERNAL_SERVER_ERROR
        }

        // Other
        data class UnknownError(
            override val statusCode: Int
        ) : HttpError()
    }

    object TimeoutError : NetworkError()
    object Unknown : NetworkError()
}

internal fun Throwable.toNetworkError(): NetworkError = when (this) {
    is HttpException -> code().toHttpError()
    is SocketTimeoutException -> NetworkError.TimeoutError
    is IOException -> Unknown
    else -> throw this
}

private fun Int.toHttpError(): HttpError = when (this) {
    BadRequst.statusCode -> BadRequst
    Unauthorized.statusCode -> Unauthorized
    NotFound.statusCode -> NotFound
    InternalServerError.statusCode -> InternalServerError
    else -> UnknownError(this)
}