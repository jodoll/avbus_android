package com.johannesdoll.avbus.ui.remotecontrol.view

import androidx.lifecycle.ViewModel
import com.johannesdoll.avbus.core.usecase.remotecontrol.RemoteControlUseCase
import com.johannesdoll.avbus.ui.remotecontrol.di.RemoteControlComponent
import javax.inject.Inject

class RemoteControlViewModel : ViewModel() {

    @Inject
    lateinit var useCase: RemoteControlUseCase

    init {
        RemoteControlComponent.factory()
            .create()
            .inject(this)
    }
}
