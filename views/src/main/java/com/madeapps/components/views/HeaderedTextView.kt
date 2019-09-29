package com.madeapps.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible

/**
 * Defines a view representing read-only text with a header component.
 */
class HeaderedTextView : CustomView {

    private var _hideIfNullOrEmpty: Boolean = false
    private var textTextView: TextView? = null
    private var headerTextView: TextView? = null
    private var rootContainer: ViewGroup? = null

    /**
     * Gets or sets a value indicating whether to hide the control if the Text is null or empty.
     */
    var hideIfNullOrEmpty: Boolean
        get() = _hideIfNullOrEmpty
        set(value){
            _hideIfNullOrEmpty = value
            updateVisibility()
        }

    /**
     * Gets or sets the string associated with the header.
     */
    var header: String?
        get() = headerTextView?.text.toString()
        set(value){
            if(headerTextView != null){
                headerTextView!!.text = value
                updateVisibility()
            }
        }

    /**
     * Gets or sets the string associated with the text.
     */
    var text: String?
        get() = textTextView?.text.toString()
        set(value){
            if(textTextView != null) {
                textTextView!!.text = value
                updateVisibility()
            }
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

    override fun init(attrs: AttributeSet?, defStyle: Int) {
        super.init(attrs, defStyle)

        view = View.inflate(context, R.layout.headeredtextview, this);

        // Load controls
        rootContainer = view?.findViewById<ViewGroup>(R.id.headeredtextview_root);
        headerTextView = view?.findViewById<TextView>(R.id.headeredtextblock_header);
        textTextView = view?.findViewById<TextView>(R.id.headeredtextblock_text);

        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.HeaderedTextView, defStyle, 0
        )

        _hideIfNullOrEmpty = a.getBoolean(R.styleable.HeaderedTextView_hide_if_null_or_empty, false)

        if(headerTextView != null){
            val header = a.getString(R.styleable.HeaderedTextView_header)

            if(!header.isNullOrEmpty()){
                headerTextView!!.text = header
            }
        }

        if(textTextView != null){
            val text = a.getString(R.styleable.HeaderedTextView_text)

            if(!text.isNullOrEmpty()){
                textTextView!!.text = text
            }
        }

        a.recycle()

        updateVisibility()
    }

    /**
     * Updates the visibility of the view based on the values of the header and text properties.
     */
    fun updateVisibility(){
        if(!hideIfNullOrEmpty || !text.isNullOrEmpty()){
            isVisible = true
            headerTextView?.isVisible = !header.isNullOrEmpty()
            textTextView?.isVisible = !text.isNullOrEmpty()
        } else {
            isVisible = false
            headerTextView?.isVisible = false
            textTextView?.isVisible = false
        }
    }
}
