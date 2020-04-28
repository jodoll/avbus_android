package com.johannesdoll.avbus.dataaccess.remotecontrol

import com.johannesdoll.avbus.core.usecase.remotecontrol.RemoteControlDataAccess
import com.johannesdoll.avbus.core.usecase.remotecontrol.RemoteControlDataAccess.Error
import com.johannesdoll.avbus.dataaccess.data.Command
import com.johannesdoll.avbus.dataaccess.rest.NetworkError
import com.johannesdoll.avbus.dataaccess.rest.executeNetworkCall
import com.johannesdoll.avbus.dataaccess.rest.remotecontrol.RetrofitFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import javax.inject.Inject

internal class RemoteControlDataAccessImpl @Inject constructor(
    moshi: Moshi,
    client: OkHttpClient
) : RemoteControlDataAccess {

    private val remoteApi = RetrofitFactory(moshi, client)
        .createRemoteControlApi("192.168.2.194")

    override suspend fun getDevices(): List<RemoteControlDataAccess.Device> {
        TODO("not implemented")
    }

    override suspend fun getCommandsByDevice(deviceId: String): List<RemoteControlDataAccess.Command> {
        TODO("not implemented")
    }

    override suspend fun sendCommand(commandId: String) = remoteApi
        .executeNetworkCall { sendCommand(Command.TunerCommand.TUNE_UP) }
        .mapLeft { it.toDataAccessError() }

    private fun NetworkError.toDataAccessError() = when (this) {
        is NetworkError.HttpError -> Error.TransportError(statusCode.toString())
        NetworkError.TimeoutError -> Error.Timeout
        NetworkError.Unknown -> Error.Unknown
    }
}
