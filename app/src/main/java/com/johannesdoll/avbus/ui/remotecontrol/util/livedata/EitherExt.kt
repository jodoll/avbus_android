package com.johannesdoll.avbus.ui.remotecontrol.util.livedata

import arrow.core.Either

fun <A, B> Either<A, B>.onLeft(block: (A) -> Unit): Either<A, B> {
    if (this is Either.Left) block(this.a)
    return this
}

fun <A, B> Either<A, B>.onRight(block: (B) -> Unit): Either<A, B> {
    if (this is Either.Right) block(this.b)
    return this
}