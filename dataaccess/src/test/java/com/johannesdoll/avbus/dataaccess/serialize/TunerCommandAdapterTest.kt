package com.johannesdoll.avbus.dataaccess.serialize

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.johannesdoll.avbus.dataaccess.data.Command
import com.johannesdoll.avbus.dataaccess.moshi.adapter
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Test

internal class TunerCommandAdapterTest {

    private val moshi = Moshi.Builder()
        .add(CommandAdapter)
        .build()

    @Test
    fun `given a TunerCommand value, it can be deserialized by moshi`() {
        val json = "TUNE_UP"

        val command = moshi.adapter<Command.TunerCommand>()
            .fromJsonValue(json)

        assertThat(command).isEqualTo(Command.TunerCommand.TUNE_UP)
    }

    @Test
    fun `given a TunerCommand, it can be serialized by moshi`() {
        val command: Command.TunerCommand = Command.TunerCommand.TUNE_DOWN

        val json = moshi.adapter<Command.TunerCommand>()
            .toJson(command)

        assertThat(json).isEqualTo("\"TUNE_DOWN\"")
    }
}