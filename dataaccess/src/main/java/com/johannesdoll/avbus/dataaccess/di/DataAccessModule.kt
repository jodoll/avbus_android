package com.johannesdoll.avbus.dataaccess.di

import com.johannesdoll.avbus.core.usecase.remotecontrol.RemoteControlDataAccess
import com.johannesdoll.avbus.dataaccess.remotecontrol.RemoteControlDataAccessImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataAccessModule {

    abstract fun remoteControlDataAccess(): RemoteControlDataAccess

    @Binds
    internal abstract fun bindRemoteControlDataAccess(dataAccessImpl: RemoteControlDataAccessImpl): RemoteControlDataAccess
}