package com.johannesdoll.avbus.ui.remotecontrol.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.use
import androidx.core.view.setPadding
import com.johannesdoll.avbus.R
import com.johannesdoll.avbus.ui.remotecontrol.util.context.getDimensionPixelOffset
import com.johannesdoll.avbus.ui.remotecontrol.util.context.getThemeValue


class RemoteControlImageButton : LinearLayout {

    private lateinit var icon: ImageView
    private lateinit var label: TextView

    constructor(context: Context) : super(context) {
        createView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        createView(context)
        applyStyledAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        createView(context)
        applyStyledAttributes(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        createView(context)
        applyStyledAttributes(attrs)
    }

    private fun createView(context: Context) {
        setBaseAttributes(context)
        inflateView(context)
    }

    private fun setBaseAttributes(context: Context) {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(context.getDimensionPixelOffset(R.dimen.button_remote_control_padding))

        val background = context.getThemeValue(android.R.attr.selectableItemBackground)
        setBackgroundResource(background.resourceId)
    }

    private fun inflateView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.button_remote_control_image, this)
        icon = findViewById(R.id.image)
        label = findViewById(R.id.label)
    }

    private fun applyStyledAttributes(attrs: AttributeSet?) {
        attrs?.extractStyledAttributes()
            ?.let { setStyledAttributes(it) }
    }

    private fun AttributeSet.extractStyledAttributes() = context
        .obtainStyledAttributes(this, R.styleable.RemoteControlImageButton)
        .use {
            StyledAttributes(
                label = it.getString(R.styleable.RemoteControlImageButton_android_text),
                icon = it.getDrawable(R.styleable.RemoteControlImageButton_android_src)
            )
        }

    private fun setStyledAttributes(attributes: StyledAttributes) {
        label.text = attributes.label
        this.icon.setImageDrawable(attributes.icon)
    }

    private data class StyledAttributes(
        val label: String?,
        val icon: Drawable?
    )
}