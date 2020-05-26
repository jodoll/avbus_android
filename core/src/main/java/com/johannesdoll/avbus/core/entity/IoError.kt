package com.johannesdoll.avbus.core.entity

sealed class IoError {
    data class TransportError(
        val reason: String
    ) : IoError()

    object Timeout : IoError()
    object Unknown : IoError()
}