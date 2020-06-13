package com.johannesdoll.avbus.dataaccess.serialize

import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.Command.*
import com.johannesdoll.avbus.core.entity.Device
import com.squareup.moshi.*


internal object CommandAdapter {
    @ToJson
    fun toJson(
        jsonWriter: JsonWriter,
        command: Command,
        deviceAdapter: JsonAdapter<Device>,
        tunerAdapter: JsonAdapter<TunerCommand>,
        ampAdapter: JsonAdapter<AmpCommand>,
        tapeAdapter: JsonAdapter<TapeCommand>,
        vcrAdapter: JsonAdapter<VcrCommand>,
        phonoAdapter: JsonAdapter<PhonoCommand>,
        cdAdapter: JsonAdapter<CdCommand>
    ) {
        val writer = CommandWriter(
            jsonWriter,
            deviceAdapter,
            tunerAdapter,
            ampAdapter,
            tapeAdapter,
            vcrAdapter,
            phonoAdapter,
            cdAdapter
        )
        writer.toJson(command)
    }

    private class CommandWriter(
        private val writer: JsonWriter,
        private val deviceAdapter: JsonAdapter<Device>,
        private val tunerAdapter: JsonAdapter<TunerCommand>,
        private val ampAdapter: JsonAdapter<AmpCommand>,
        private val tapeAdapter: JsonAdapter<TapeCommand>,
        private val vcrAdapter: JsonAdapter<VcrCommand>,
        private val phonoAdapter: JsonAdapter<PhonoCommand>,
        private val cdAdapter: JsonAdapter<CdCommand>
    ) {

        fun toJson(command: Command) {
            writer.beginObject()
            writer.write(command.device)
            writer.write(command)
            writer.endObject()
        }

        private fun JsonWriter.write(device: Device) {
            name("device")
            deviceAdapter.toJson(this, device)
        }

        private fun JsonWriter.write(command: Command) {
            name("command")
            when (command) {
                is TunerCommand -> {
                    tunerAdapter.toJson(this, command)
                }
                is AmpCommand -> {
                    ampAdapter.toJson(this, command)
                }
                is TapeCommand -> {
                    tapeAdapter.toJson(this, command)
                }
                is VcrCommand -> {
                    vcrAdapter.toJson(this, command)
                }
                is PhonoCommand -> {
                    phonoAdapter.toJson(this, command)
                }
                is CdCommand -> {
                    cdAdapter.toJson(this, command)
                }
            }
        }
    }

    @FromJson
    fun fromJson(
        jsonReader: JsonReader,
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
        val reader = CommandReader(
            jsonReader,
            deviceAdapter,
            tunerAdapter,
            ampAdapter,
            tapeAdapter,
            vcrAdapter,
            phonoAdapter,
            cdAdapter,
            tvAdapter,
            systemAdapter
        )
        return reader.fromJson()
    }

    private class CommandReader(
        private val reader: JsonReader,
        private val deviceAdapter: JsonAdapter<Device>,
        private val tunerAdapter: JsonAdapter<TunerCommand>,
        private val ampAdapter: JsonAdapter<AmpCommand>,
        private val tapeAdapter: JsonAdapter<TapeCommand>,
        private val vcrAdapter: JsonAdapter<VcrCommand>,
        private val phonoAdapter: JsonAdapter<PhonoCommand>,
        private val cdAdapter: JsonAdapter<CdCommand>,
        private val tvAdapter: JsonAdapter<TvCommand>,
        private val systemAdapter: JsonAdapter<SystemCommand>
    ) {

        fun fromJson(): Command? {
            val command = readJsonCommand(reader)
            return command.toCommand()
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

        private fun JsonCommand.toCommand(): Command? {
            return when (deviceAdapter.fromJsonValue(device)) {
                Device.Tuner -> tunerAdapter.fromJsonValue(command)
                Device.Tv -> tvAdapter.fromJsonValue(command)
                Device.Amp -> ampAdapter.fromJsonValue(command)
                Device.Tape -> tapeAdapter.fromJsonValue(command)
                Device.Vcr -> vcrAdapter.fromJsonValue(command)
                Device.Phono -> phonoAdapter.fromJsonValue(command)
                Device.Cd -> cdAdapter.fromJsonValue(command)
                Device.System -> systemAdapter.fromJsonValue(command)
                null -> null
            }
        }

        private data class JsonCommand(
            val device: String,
            val command: String
        )
    }

}