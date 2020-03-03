package com.johannesdoll.avbus.dataaccess.json

import com.johannesdoll.avbus.dataaccess.data.Command
import com.johannesdoll.avbus.dataaccess.data.Device
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class JsonCommand<T : Device>(
    @Json(name = "device")
    val device: T,
    @Json(name = "command")
    val command: Command<Device>
)