package com.johannesdoll.avbus.core.application.send.command.port.`in`

import arrow.core.Either
import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.IoError

interface SendCommandUseCase {
    suspend fun sendCommand(command: Command): Either<IoError, Unit>
}