package com.johannesdoll.avbus.dataaccess.rest.remotecontrol

import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.dataaccess.serialize.CommandAdapter
import com.johannesdoll.avbus.dataaccess.serialize.DeviceAdapter
import com.johannesdoll.avbus.dataaccess.serialize.SpecificCommandAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.junit.jupiter.api.Test

internal class RemoteControlApiTest {

    private val moshi = Moshi.Builder()
        .add(CommandAdapter)
        .add(SpecificCommandAdapter)
        .add(DeviceAdapter)
        .build()

    private fun createApiWithResponse(block: (Interceptor.Chain) -> Response): RemoteControlApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(block)
            .build()
        return RetrofitFactory(moshi, client).createRemoteControlApi("localhost")
    }

    @Test
    fun `when executing sendCommand, no exception is thrown`() {
        val restApi = createApiWithResponse {
            val content = "{\"success\": true}"
            Response.Builder()
                .request(it.request())
                .code(200)
                .addHeader("content-type", "application/json")
                .protocol(Protocol.HTTP_1_1)
                .message(content)
                .body(
                    ResponseBody.create(
                        MediaType.get("application/json"),
                        content
                    )
                ).build()
        }
        runBlocking {
            restApi.sendCommand(Command.CdCommand.PAUSE)
        }
    }


}