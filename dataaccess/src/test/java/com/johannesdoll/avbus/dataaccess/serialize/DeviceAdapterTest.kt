package com.johannesdoll.avbus.dataaccess.serialize

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.johannesdoll.avbus.core.entity.Device
import com.johannesdoll.avbus.dataaccess.moshi.adapter
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Test

internal class DeviceAdapterTest {

    private val moshi = Moshi.Builder()
        .add(DeviceAdapter)
        .build()

    @Test
    fun `given a Device value, it can be deserialized by moshi`() {
        val json = "AMP"

        val command = moshi.adapter<Device>()
            .fromJsonValue(json)

        assertThat(command).isEqualTo(Device.Amp)
    }

    @Test
    fun `given a Device, it can be serialized by moshi`() {
        val device: Device = Device.Tv

        val json = moshi.adapter<Device>()
            .toJson(device)

        assertThat(json).isEqualTo("\"TV\"")
    }
}