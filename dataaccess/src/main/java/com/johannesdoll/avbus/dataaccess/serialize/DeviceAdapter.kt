package com.johannesdoll.avbus.dataaccess.serialize

import com.johannesdoll.avbus.dataaccess.data.Device
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

internal object DeviceAdapter {
    @FromJson
    fun fromJson(name: String): Device? = when (name) {
        "TUNER" -> Device.Tuner
        "TV" -> Device.Tv
        "AMP" -> Device.Amp
        "TAPE" -> Device.Tape
        "VCR" -> Device.Vcr
        "PHONO" -> Device.Phono
        "CD" -> Device.Cd
        "SYSTEM" -> Device.System
        else -> null
    }

    @ToJson
    fun toJson(name: Device): String = when (name) {
        Device.Tuner -> "TUNER"
        Device.Tv -> "TV"
        Device.Amp -> "AMP"
        Device.Tape -> "TAPE"
        Device.Vcr -> "VCR"
        Device.Phono -> "PHONO"
        Device.Cd -> "CD"
        Device.System -> "SYSTEM"
    }
}