package com.johannesdoll.avbus.ui.remotecontrol.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.johannesdoll.avbus.core.application.send.command.port.`in`.SendCommandUseCase
import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.IoError
import com.johannesdoll.avbus.ui.remotecontrol.di.RemoteControlComponent
import com.johannesdoll.avbus.ui.remotecontrol.util.livedata.onLeft
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RemoteControlViewModel
    : ViewModel(),
    RemoteControlContract.Controller,
    RemoteControlContract.Model {

    @Inject
    lateinit var useCase: SendCommandUseCase

    private val ioError = LiveEvent<String>()

    init {
        RemoteControlComponent.factory()
            .create()
            .inject(this)
    }

    override fun sendCommand(command: Command) {
        viewModelScope.launch {
            useCase.sendCommand(command)
                .onLeft {
                    Timber.e(it.toString())
                    handeIoError(it)
                }
        }
    }

    private fun handeIoError(error: IoError) {
        ioError.value = when (error) {
            is IoError.TransportError -> error.reason //TODO: localize?
            is IoError.Unknown -> "Unknown Error Occurred" //TODO: extract
            IoError.Timeout -> "Timeout Occurred" //TODO: extract
        }
    }

    override fun ioError() = ioError
}
