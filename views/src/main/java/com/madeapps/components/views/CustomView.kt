package com.madeapps.components.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Defines a UI element for creating custom layout based controls.
 */
open class CustomView : FrameLayout {

    private var _view: View? = null

    /**
     * Gets the ID for the layout associated with the view.
     */
    open val layoutId: Int = 0;

    /**
     * Gets the view associated with the inflated layout.
     */
    var view: View?
        get() = this._view
        protected set(value) {
            this._view = value
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    open fun init(attrs: AttributeSet?, defStyle: Int) {
    }
}