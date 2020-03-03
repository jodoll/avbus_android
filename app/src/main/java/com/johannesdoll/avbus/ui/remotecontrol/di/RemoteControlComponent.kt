package com.johannesdoll.avbus.ui.remotecontrol.di

import com.johannesdoll.avbus.core.usecase.di.RemoteControlUseCaseModule
import com.johannesdoll.avbus.dataaccess.di.DataAccessModule
import com.johannesdoll.avbus.ui.remotecontrol.view.RemoteControlViewModel
import dagger.Component

@Component(
    modules = [
        RemoteControlUseCaseModule::class,
        DataAccessModule::class
    ]
)
interface RemoteControlComponent {

    companion object {
        fun factory(): Factory = DaggerRemoteControlComponent.factory()
    }

    fun inject(viewModel: RemoteControlViewModel)

    @Component.Factory
    interface Factory {
        fun create(): RemoteControlComponent
    }
}