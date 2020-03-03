package com.johannesdoll.avbus.dataaccess.serialize

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.johannesdoll.avbus.dataaccess.data.Command
import com.johannesdoll.avbus.dataaccess.moshi.adapter
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Test

class CommandAdapterTest {

    private val moshi = Moshi.Builder()
        .add(CommandAdapter)
        .add(SpecificCommandAdapter)
        .add(DeviceAdapter)
        .build()

    @Test
    fun `given a command, it can be deserialized with Moshi`() {
        val json = "{\"device\": \"TUNER\", \"command\": \"TUNE_UP\"}"

        val command = moshi.adapter<Command>().fromJson(json)

        assertThat(command)
            .isNotNull()
            .isEqualTo(Command.TunerCommand.TUNE_UP)
    }

    @Test
    fun `given a command, it can be serialized with Moshi`() {
        val command = Command.AmpCommand.INPUT_CD

        val json = moshi.adapter<Command>().toJson(command)

        assertThat(json).all {
            contains("AMP")
            contains("INPUT_CD")
        }
    }
}