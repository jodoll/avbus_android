package com.johannesdoll.avbus.ui.remotecontrol.view

import androidx.lifecycle.LiveData
import com.johannesdoll.avbus.core.entity.Command

interface RemoteControlContract {

    interface Controller {
        fun sendCommand(command: Command)
    }

    interface Model {
        fun ioError(): LiveData<String>
    }
}