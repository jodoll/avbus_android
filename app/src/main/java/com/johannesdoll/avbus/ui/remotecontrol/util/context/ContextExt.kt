package com.johannesdoll.avbus.ui.remotecontrol.util.context

import android.content.Context
import androidx.annotation.DimenRes

fun Context.getDimensionPixelOffset(@DimenRes dimen: Int) =
    resources.getDimensionPixelOffset(dimen)

fun Context.getDimensionPixelSize(@DimenRes dimen: Int) =
    resources.getDimensionPixelSize(dimen)

fun Context.getDimension(@DimenRes dimen: Int) =
    resources.getDimension(dimen)
