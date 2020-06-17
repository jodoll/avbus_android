package com.johannesdoll.avbus.dataaccess.rest.remotecontrol

import com.johannesdoll.avbus.core.entity.Command
import retrofit2.http.Body
import retrofit2.http.POST

internal interface RemoteControlApi {

    @POST("remote/command")
    suspend fun sendCommand(@Body command: Command)
}