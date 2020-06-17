package com.johannesdoll.avbus.dataaccess.di

import com.johannesdoll.avbus.core.application.send.command.port.out.SendCommandOutputPort
import com.johannesdoll.avbus.dataaccess.BuildConfig
import com.johannesdoll.avbus.dataaccess.remotecontrol.SendCommandAdapter
import com.johannesdoll.avbus.dataaccess.serialize.CommandAdapter
import com.johannesdoll.avbus.dataaccess.serialize.DeviceAdapter
import com.johannesdoll.avbus.dataaccess.serialize.SpecificCommandAdapter
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
abstract class DataAccessModule {

    abstract fun remoteControlDataAccess(): SendCommandOutputPort

    @Binds
    internal abstract fun bindRemoteControlDataAccess(adapter: SendCommandAdapter): SendCommandOutputPort

    companion object Provider {
        @Provides
        internal fun provideMoshi() = Moshi.Builder()
            .add(CommandAdapter)
            .add(SpecificCommandAdapter)
            .add(DeviceAdapter)
            .build()

        @Provides
        internal fun provideOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    }

}