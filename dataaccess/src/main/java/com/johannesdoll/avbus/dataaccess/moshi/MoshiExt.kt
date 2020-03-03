package com.johannesdoll.avbus.dataaccess.moshi

import com.squareup.moshi.Moshi

inline fun <reified T> Moshi.adapter() = adapter<T>(T::class.java)