package com.johannesdoll.avbus.dataaccess.rest

import arrow.core.Either

internal suspend fun <R, T> R.executeNetworkCall(block: suspend R.() -> T) = Either
    .catch { block() }
    .mapLeft { it.toNetworkError() }

