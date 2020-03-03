package com.johannesdoll.avbus.core.usecase.di

import com.johannesdoll.avbus.core.usecase.remotecontrol.RemoteControlUseCase
import com.johannesdoll.avbus.core.usecase.remotecontrol.RemoteControlUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteControlUseCaseModule {

    abstract fun remoteControlUseCase(): RemoteControlUseCase

    @Binds
    internal abstract fun bindRemoteControlUseCase(useCaseImpl: RemoteControlUseCaseImpl): RemoteControlUseCase
}