package com.johannesdoll.avbus.ui.remotecontrol.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.content.res.use
import androidx.core.view.isInvisible
import androidx.core.view.setPadding
import com.johannesdoll.avbus.R
import com.johannesdoll.avbus.ui.remotecontrol.util.context.getDimensionPixelOffset
import com.johannesdoll.avbus.ui.remotecontrol.util.context.getThemeValue


class RemoteControlButton : LinearLayout {

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
        LayoutInflater.from(context).inflate(R.layout.button_remote_control, this)
        icon = findViewById(R.id.image)
        label = findViewById(R.id.label)
    }

    private fun applyStyledAttributes(attrs: AttributeSet?) {
        attrs?.extractStyledAttributes()
            ?.let { setStyledAttributes(it) }
    }

    private fun AttributeSet.extractStyledAttributes() = context
        .obtainStyledAttributes(this, R.styleable.RemoteControlButton)
        .use {
            StyledAttributes(
                label = it.getString(R.styleable.RemoteControlButton_android_text),
                icon = it.getDrawable(R.styleable.RemoteControlButton_android_src),
                background = it.getDrawable(R.styleable.RemoteControlButton_background),
                textAppearance = it.getResourceId(
                    R.styleable.RemoteControlButton_textAppearance,
                    0
                ),
                isFilled = it.getBoolean(R.styleable.RemoteControlButton_filled, false)
            )
        }

    private fun setStyledAttributes(attributes: StyledAttributes) {
        label.apply {
            text = attributes.label
            if (attributes.textAppearance != 0) setTextAppearance(attributes.textAppearance)
            if (attributes.isFilled) setBackgroundResource(R.drawable.backgound_remote_button_filled)
        }
        setImageDrawable(attributes.icon)
        attributes.background?.let { background = it }
    }

    private data class StyledAttributes(
        val label: String?,
        val icon: Drawable?,
        val background: Drawable?,
        @StyleRes val textAppearance: Int,
        val isFilled: Boolean
    )

    var text: CharSequence?
        get() = label.text
        set(text) {
            label.text = text
        }

    fun setImageDrawable(drawable: Drawable?) {
        icon.apply {
            setImageDrawable(drawable)
            isInvisible = drawable == null
        }
    }
}