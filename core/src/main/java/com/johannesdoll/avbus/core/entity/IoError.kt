package com.johannesdoll.avbus.core.entity

sealed class IoError {
    data class TransportError(
        val reason: String
    ) : IoError()

    data class Unknown(
        val message: String
    ) : IoError()

    object Timeout : IoError()
}