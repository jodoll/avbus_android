package com.johannesdoll.avbus.ui.remotecontrol.util.context

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes

fun Context.getDimensionPixelOffset(@DimenRes dimen: Int) =
    resources.getDimensionPixelOffset(dimen)

fun Context.getDimensionPixelSize(@DimenRes dimen: Int) =
    resources.getDimensionPixelSize(dimen)

fun Context.getDimension(@DimenRes dimen: Int) =
    resources.getDimension(dimen)

fun Context.getThemeValue(@AttrRes attribute: Int): TypedValue {
    val resolvedAttribute = TypedValue()
    theme.resolveAttribute(attribute, resolvedAttribute, true)
    return resolvedAttribute
}
