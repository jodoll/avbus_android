package com.johannesdoll.avbus.dataaccess.data


internal sealed class Device {

    object Tuner : Device()

    object Tv : Device()

    object Amp : Device()

    object Tape : Device()

    object Vcr : Device()

    object Phono : Device()

    object Cd : Device()

    object System : Device()
}

