package com.johannesdoll.avbus.dataaccess.serialize

import com.johannesdoll.avbus.dataaccess.data.Command
import com.johannesdoll.avbus.dataaccess.data.Command.*
import com.johannesdoll.avbus.dataaccess.data.Device
import com.squareup.moshi.*

internal object CommandAdapter {
    @ToJson
    fun toJson(
        writer: JsonWriter,
        command: Command,
        deviceAdapter: JsonAdapter<Device>,
        tunerAdapter: JsonAdapter<TunerCommand>,
        ampAdapter: JsonAdapter<AmpCommand>,
        tapeAdapter: JsonAdapter<TapeCommand>,
        vcrAdapter: JsonAdapter<VcrCommand>,
        phonoAdapter: JsonAdapter<PhonoCommand>,
        cdAdapter: JsonAdapter<CdCommand>
    ) {
        writer.beginObject()
        writer.name("device")
        deviceAdapter.toJson(writer, command.device)
        when (command) {
            is TunerCommand -> {
                writer.name("command")
                tunerAdapter.toJson(writer, command)
            }
            is AmpCommand -> {
                writer.name("command")
                ampAdapter.toJson(writer, command)
            }
            is TapeCommand -> {
                writer.name("command")
                tapeAdapter.toJson(writer, command)
            }
            is VcrCommand -> {
                writer.name("command")
                vcrAdapter.toJson(writer, command)
            }
            is PhonoCommand -> {
                writer.name("command")
                phonoAdapter.toJson(writer, command)
            }
            is CdCommand -> {
                writer.name("command")
                cdAdapter.toJson(writer, command)
            }
        }
        writer.endObject()
    }

    @FromJson
    fun fromJson(
        reader: JsonReader,
        deviceAdapter: JsonAdapter<Device>,
        tunerAdapter: JsonAdapter<TunerCommand>,
        ampAdapter: JsonAdapter<AmpCommand>,
        tapeAdapter: JsonAdapter<TapeCommand>,
        vcrAdapter: JsonAdapter<VcrCommand>,
        phonoAdapter: JsonAdapter<PhonoCommand>,
        cdAdapter: JsonAdapter<CdCommand>,
        tvAdapter: JsonAdapter<TvCommand>,
        systemAdapter: JsonAdapter<SystemCommand>
    ): Command? {
        val command = readJsonCommand(reader)
        return when (deviceAdapter.fromJsonValue(command.device)) {
            Device.Tuner -> tunerAdapter.fromJsonValue(command.command)
            Device.Tv -> tvAdapter.fromJsonValue(command.command)
            Device.Amp -> ampAdapter.fromJsonValue(command.command)
            Device.Tape -> tapeAdapter.fromJsonValue(command.command)
            Device.Vcr -> vcrAdapter.fromJsonValue(command.command)
            Device.Phono -> phonoAdapter.fromJsonValue(command.command)
            Device.Cd -> cdAdapter.fromJsonValue(command.command)
            Device.System -> systemAdapter.fromJsonValue(command.command)
            null -> null
        }
    }

    private fun readJsonCommand(reader: JsonReader): JsonCommand {
        reader.beginObject()
        var deviceString: String? = null
        var commandString: String? = null
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "device" -> deviceString = reader.nextString()
                "command" -> commandString = reader.nextString()
            }
        }
        reader.endObject()

        checkNotNull(deviceString) { "$reader is missing 'device'" }
        checkNotNull(commandString) { "$reader is missing 'command'" }
        return JsonCommand(deviceString, commandString)
    }

    data class JsonCommand(
        val device: String,
        val command: String
    )
}