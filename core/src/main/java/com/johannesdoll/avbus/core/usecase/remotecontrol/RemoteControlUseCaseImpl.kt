package com.johannesdoll.avbus.core.usecase.remotecontrol

import javax.inject.Inject

internal class RemoteControlUseCaseImpl @Inject constructor(
    dataAccess: RemoteControlDataAccess
) : RemoteControlUseCase