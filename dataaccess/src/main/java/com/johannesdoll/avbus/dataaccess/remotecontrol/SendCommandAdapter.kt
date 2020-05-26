package com.johannesdoll.avbus.dataaccess.remotecontrol

import com.johannesdoll.avbus.core.application.send.command.port.out.SendCommandOutputPort
import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.IoError
import com.johannesdoll.avbus.dataaccess.rest.NetworkError
import com.johannesdoll.avbus.dataaccess.rest.executeNetworkCall
import com.johannesdoll.avbus.dataaccess.rest.remotecontrol.RetrofitFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import javax.inject.Inject

internal class SendCommandAdapter @Inject constructor(
    moshi: Moshi,
    client: OkHttpClient
) : SendCommandOutputPort {

    private val remoteApi = RetrofitFactory(moshi, client)
        .createRemoteControlApi("192.168.2.194")

    override suspend fun sendCommand(command: Command) = remoteApi
        .executeNetworkCall { sendCommand(command) }
        .mapLeft { it.toIoError() }

    private fun NetworkError.toIoError() = when (this) {
        is NetworkError.HttpError -> IoError.TransportError(statusCode.toString())
        NetworkError.TimeoutError -> IoError.Timeout
        NetworkError.Unknown -> IoError.Unknown
    }
}
