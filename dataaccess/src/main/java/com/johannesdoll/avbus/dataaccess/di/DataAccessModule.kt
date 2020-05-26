package com.johannesdoll.avbus.dataaccess.di

import com.johannesdoll.avbus.core.application.send.command.port.out.SendCommandOutputPort
import com.johannesdoll.avbus.dataaccess.remotecontrol.SendCommandAdapter
import com.johannesdoll.avbus.dataaccess.serialize.DeviceAdapter
import com.johannesdoll.avbus.dataaccess.serialize.SpecificCommandAdapter
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
abstract class DataAccessModule {

    abstract fun remoteControlDataAccess(): SendCommandOutputPort

    @Binds
    internal abstract fun bindRemoteControlDataAccess(adapter: SendCommandAdapter): SendCommandOutputPort

    companion object Provider {
        @Provides
        internal fun provideMoshi() = Moshi.Builder()
            .add(SpecificCommandAdapter)
            .add(DeviceAdapter)
            .build()

        @Provides
        internal fun provideOkHttpClient() = OkHttpClient.Builder()
            .build()

    }

}