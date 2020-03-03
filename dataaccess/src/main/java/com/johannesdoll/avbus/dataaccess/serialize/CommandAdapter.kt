package com.johannesdoll.avbus.dataaccess.serialize

import com.johannesdoll.avbus.dataaccess.data.Command.*
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

internal object CommandAdapter {
    @FromJson
    fun tunerCommandFromJson(name: String): TunerCommand? = when (name) {
        "AM" -> TunerCommand.AM
        "FM" -> TunerCommand.FM
        "TUNE_DOWN" -> TunerCommand.TUNE_DOWN
        "TUNE_UP" -> TunerCommand.TUNE_UP
        "MEMORY_1" -> TunerCommand.MEMORY_1
        "MEMORY_2" -> TunerCommand.MEMORY_2
        "MEMORY_3" -> TunerCommand.MEMORY_3
        "MEMORY_4" -> TunerCommand.MEMORY_4
        "MEMORY_5" -> TunerCommand.MEMORY_5
        "MEMORY_6" -> TunerCommand.MEMORY_6
        "MEMORY_7" -> TunerCommand.MEMORY_7
        "MEMORY_8" -> TunerCommand.MEMORY_8
        else -> null
    }

    @ToJson
    fun tunerCommandToJson(name: TunerCommand): String = when (name) {
        TunerCommand.AM -> "AM"
        TunerCommand.FM -> "FM"
        TunerCommand.TUNE_DOWN -> "TUNE_DOWN"
        TunerCommand.TUNE_UP -> "TUNE_UP"
        TunerCommand.MEMORY_1 -> "MEMORY_1"
        TunerCommand.MEMORY_2 -> "MEMORY_2"
        TunerCommand.MEMORY_3 -> "MEMORY_3"
        TunerCommand.MEMORY_4 -> "MEMORY_4"
        TunerCommand.MEMORY_5 -> "MEMORY_5"
        TunerCommand.MEMORY_6 -> "MEMORY_6"
        TunerCommand.MEMORY_7 -> "MEMORY_7"
        TunerCommand.MEMORY_8 -> "MEMORY_8"
    }

    @FromJson
    fun ampCommandFromJson(name: String): AmpCommand? = when (name) {
        "VOLUME_DOWN_FRONT" -> AmpCommand.VOLUME_DOWN_FRONT
        "VOLUME_UP_FRONT" -> AmpCommand.VOLUME_UP_FRONT
        "VOLUME_DOWN_REAR" -> AmpCommand.VOLUME_DOWN_REAR
        "VOLUME_UP_REAR" -> AmpCommand.VOLUME_UP_REAR
        "MUTE" -> AmpCommand.MUTE
        "INPUT_TUNER" -> AmpCommand.INPUT_TUNER
        "INPUT_PHONO" -> AmpCommand.INPUT_PHONO
        "INPUT_CD" -> AmpCommand.INPUT_CD
        "INPUT_TAPE_MON" -> AmpCommand.INPUT_TAPE_MON
        "INPUT_TV" -> AmpCommand.INPUT_TV
        "INPUT_VIDEO" -> AmpCommand.INPUT_VIDEO
        "INPUT_VCR" -> AmpCommand.INPUT_VCR
        else -> null
    }

    @ToJson
    fun ampCommandToJson(name: AmpCommand): String = when (name) {
        AmpCommand.VOLUME_DOWN_FRONT -> "VOLUME_DOWN_FRONT"
        AmpCommand.VOLUME_UP_FRONT -> "VOLUME_UP_FRONT"
        AmpCommand.VOLUME_DOWN_REAR -> "VOLUME_DOWN_REAR"
        AmpCommand.VOLUME_UP_REAR -> "VOLUME_UP_REAR"
        AmpCommand.MUTE -> "MUTE"
        AmpCommand.INPUT_TUNER -> "INPUT_TUNER"
        AmpCommand.INPUT_PHONO -> "INPUT_PHONO"
        AmpCommand.INPUT_CD -> "INPUT_CD"
        AmpCommand.INPUT_TAPE_MON -> "INPUT_TAPE_MON"
        AmpCommand.INPUT_TV -> "INPUT_TV"
        AmpCommand.INPUT_VIDEO -> "INPUT_VIDEO"
        AmpCommand.INPUT_VCR -> "INPUT_VCR"
    }

    @FromJson
    fun tapeCommandFromJson(name: String): TapeCommand? = when (name) {
        "PLAY_RPT" -> TapeCommand.PLAY_RPT
        "STOP_C" -> TapeCommand.STOP_C
        "REW" -> TapeCommand.REW
        "FF" -> TapeCommand.FF
        "PAUSE" -> TapeCommand.PAUSE
        "DIRECTION" -> TapeCommand.DIRECTION
        "INDEX_SCAN" -> TapeCommand.INDEX_SCAN
        "QMS" -> TapeCommand.QMS
        "MEMO" -> TapeCommand.MEMO
        "REC" -> TapeCommand.REC
        "REC_MUTE" -> TapeCommand.REC_MUTE
        else -> null
    }

    @ToJson
    fun tapeCommandToJson(name: TapeCommand): String = when (name) {
        TapeCommand.PLAY_RPT -> "PLAY_RPT"
        TapeCommand.STOP_C -> "STOP_C"
        TapeCommand.REW -> "REW"
        TapeCommand.FF -> "FF"
        TapeCommand.PAUSE -> "PAUSE"
        TapeCommand.DIRECTION -> "DIRECTION"
        TapeCommand.INDEX_SCAN -> "INDEX_SCAN"
        TapeCommand.QMS -> "QMS"
        TapeCommand.MEMO -> "MEMO"
        TapeCommand.REC -> "REC"
        TapeCommand.REC_MUTE -> "REC_MUTE"
    }

    @FromJson
    fun vcrCommandFromJson(name: String): VcrCommand? = when (name) {
        "DIRECTION" -> VcrCommand.DIRECTION
        "MEMO" -> VcrCommand.MEMO
        "REC_MUTE" -> VcrCommand.REC_MUTE
        else -> null
    }

    @ToJson
    fun vcrCommandToJson(name: VcrCommand): String = when (name) {
        VcrCommand.DIRECTION -> "DIRECTION"
        VcrCommand.MEMO -> "MEMO"
        VcrCommand.REC_MUTE -> "REC_MUTE"
    }

    @FromJson
    fun phonoCommandFromJson(name: String): PhonoCommand? = when (name) {
        "PLAY" -> PhonoCommand.PLAY
        "CUT" -> PhonoCommand.CUT
        "CUE" -> PhonoCommand.CUE
        "REPEAT" -> PhonoCommand.REPEAT
        else -> null
    }

    @ToJson
    fun phonoCommandToJson(name: PhonoCommand): String = when (name) {
        PhonoCommand.PLAY -> "PLAY"
        PhonoCommand.CUT -> "CUT"
        PhonoCommand.CUE -> "CUE"
        PhonoCommand.REPEAT -> "REPEAT"
    }

    @FromJson
    fun cdCommandFromJson(name: String): CdCommand? = when (name) {
        "PLAY_NEXT" -> CdCommand.PLAY_NEXT
        "STOP_C" -> CdCommand.STOP_C
        "PAUSE" -> CdCommand.PAUSE
        "REPEAT" -> CdCommand.REPEAT
        else -> null
    }

    @ToJson
    fun cdCommandToJson(name: CdCommand): String = when (name) {
        CdCommand.PLAY_NEXT -> "PLAY_NEXT"
        CdCommand.STOP_C -> "STOP_C"
        CdCommand.PAUSE -> "PAUSE"
        CdCommand.REPEAT -> "REPEAT"
    }
}