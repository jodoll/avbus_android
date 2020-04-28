package com.johannesdoll.avbus.dataaccess.moshi

import com.squareup.moshi.Moshi

internal inline fun <reified T> Moshi.adapter() = adapter<T>(T::class.java)