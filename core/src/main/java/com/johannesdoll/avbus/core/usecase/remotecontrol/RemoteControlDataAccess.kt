package com.johannesdoll.avbus.core.usecase.remotecontrol

import arrow.core.Either

interface RemoteControlDataAccess {

    suspend fun getDevices(): List<Device>
    suspend fun getCommandsByDevice(deviceId: String): List<Command>
    suspend fun sendCommand(commandId: String): Either<Error, Unit>

    data class Device(
        val id: String,
        val name: String
    )

    data class Command(
        val id: String,
        val deviceId: String,
        val name: String
    )

    sealed class Error {
        data class TransportError(
            val reason: String
        ) : Error()

        object Timeout : Error()
        object Unknown : Error()
    }
}