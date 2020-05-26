package com.johannesdoll.avbus.core.application.send.command.service

import arrow.core.Either
import com.johannesdoll.avbus.core.application.send.command.port.`in`.SendCommandUseCase
import com.johannesdoll.avbus.core.application.send.command.port.out.SendCommandOutputPort
import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.IoError
import javax.inject.Inject

internal class SendCommandService @Inject constructor(
    private val sendCommandOutputPort: SendCommandOutputPort
) : SendCommandUseCase {

    override suspend fun sendCommand(command: Command): Either<IoError, Unit> {
        return sendCommandOutputPort.sendCommand(command)
    }
}