package com.johannesdoll.avbus.core.di

import com.johannesdoll.avbus.core.application.send.command.port.`in`.SendCommandUseCase
import com.johannesdoll.avbus.core.application.send.command.service.SendCommandService
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteControlUseCaseModule {

    abstract fun remoteControlUseCase(): SendCommandUseCase

    @Binds
    internal abstract fun bindRemoteControlUseCase(service: SendCommandService): SendCommandUseCase
}