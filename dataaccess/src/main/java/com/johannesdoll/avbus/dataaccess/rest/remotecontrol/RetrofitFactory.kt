package com.johannesdoll.avbus.dataaccess.rest.remotecontrol

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

internal class RetrofitFactory @Inject constructor(
    private val moshi: Moshi,
    private val client: OkHttpClient
) {
    companion object {
        const val API_VERISON = 1
    }

    fun createRemoteControlApi(host: String): RemoteControlApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://$host/api/v$API_VERISON/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create()
    }
}