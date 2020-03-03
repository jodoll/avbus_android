package com.johannesdoll.avbus.dataaccess.data

internal sealed class Command {
    abstract val device: Device
    sealed class TunerCommand : Command() {
        override val device = Device.Tuner

        object AM : TunerCommand()
        object FM : TunerCommand()
        object TUNE_DOWN : TunerCommand()
        object TUNE_UP : TunerCommand()
        object MEMORY_1 : TunerCommand()
        object MEMORY_2 : TunerCommand()
        object MEMORY_3 : TunerCommand()
        object MEMORY_4 : TunerCommand()
        object MEMORY_5 : TunerCommand()
        object MEMORY_6 : TunerCommand()
        object MEMORY_7 : TunerCommand()
        object MEMORY_8 : TunerCommand()
    }

    sealed class TvCommand : Command() {
        override val device = Device.Tv
    }
    sealed class AmpCommand : Command() {
        override val device = Device.Amp

        object VOLUME_DOWN_FRONT : AmpCommand()
        object VOLUME_UP_FRONT : AmpCommand()
        object VOLUME_DOWN_REAR : AmpCommand()
        object VOLUME_UP_REAR : AmpCommand()
        object MUTE : AmpCommand()
        object INPUT_TUNER : AmpCommand()
        object INPUT_PHONO : AmpCommand()
        object INPUT_CD : AmpCommand()
        object INPUT_TAPE_MON : AmpCommand()
        object INPUT_TV : AmpCommand()
        object INPUT_VIDEO : AmpCommand()
        object INPUT_VCR : AmpCommand()
    }

    sealed class TapeCommand : Command() {
        override val device = Device.Tape

        object PLAY_RPT : TapeCommand()
        object STOP_C : TapeCommand()
        object REW : TapeCommand()
        object FF : TapeCommand()
        object PAUSE : TapeCommand()
        object DIRECTION : TapeCommand()
        object INDEX_SCAN : TapeCommand()
        object QMS : TapeCommand()
        object MEMO : TapeCommand()
        object REC : TapeCommand()
        object REC_MUTE : TapeCommand()
    }

    sealed class VcrCommand : Command() {
        override val device = Device.Vcr

        object DIRECTION : VcrCommand()
        object MEMO : VcrCommand()
        object REC_MUTE : VcrCommand()
    }

    sealed class PhonoCommand : Command() {
        override val device = Device.Phono

        object PLAY : PhonoCommand()
        object CUT : PhonoCommand()
        object CUE : PhonoCommand()
        object REPEAT : PhonoCommand()
    }

    sealed class CdCommand : Command() {
        override val device = Device.Cd

        object PLAY_NEXT : CdCommand()
        object STOP_C : CdCommand()
        object PAUSE : CdCommand()
        object REPEAT : CdCommand()
    }

    sealed class SystemCommand : Command() {
        override val device = Device.System
    }
}