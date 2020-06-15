package com.johannesdoll.avbus.core.application.send.command.port.out

import arrow.core.Either
import com.johannesdoll.avbus.core.entity.Command
import com.johannesdoll.avbus.core.entity.IoError

interface SendCommandOutputPort {

    suspend fun sendCommand(command: Command): Either<IoError, Unit>
}