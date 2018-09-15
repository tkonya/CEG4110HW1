package io.konya.ceg4110hw1

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

/**
 * TODO: document your custom view class.
 */
class CustomDrawingView : View {

    private var _exampleString: String? = null
    private var _exampleColor: Int = Color.BLACK
    private var _exampleDimension: Float = 0f

    private var textPaint: TextPaint? = null
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

    private var paints = mutableListOf<Paint>()
    private var paths = mutableListOf<Path>()

    private var debugBoxReference: TextView? = null

    /**
     * The text to draw
     */
    var exampleString: String?
        get() = _exampleString
        set(value) {
            _exampleString = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * The font color
     */
    var exampleColor: Int
        get() = _exampleColor
        set(value) {
            _exampleColor = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this dimension is the font size.
     */
    var exampleDimension: Float
        get() = _exampleDimension
        set(value) {
            _exampleDimension = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this drawable is drawn above the text.
     */

    constructor(context: Context) : super(context) {
        init(null, 0)
        initializeState()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
        initializeState()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
        initializeState()
    }

    private fun initializeState() {
        addPaint(255, 0, 0, 0)
        paths.add(Path())
    }

    fun setPaintColor(alpha: Int, red: Int, green: Int, blue: Int) {
        addPaint(alpha, red, green, blue)
        paths.add(Path())
    }

    fun addPaint(alpha: Int, red: Int, green: Int, blue: Int) {
        paints.add(Paint())
        paints[paints.lastIndex].isAntiAlias = true
        paints[paints.lastIndex].strokeWidth = 20f
        paints[paints.lastIndex].color = Color.argb(alpha, red, green, blue)
        paints[paints.lastIndex].style = Paint.Style.STROKE
        paints[paints.lastIndex].strokeJoin = Paint.Join.ROUND
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.CustomDrawingView, defStyle, 0)

        _exampleString = a.getString(
                R.styleable.CustomDrawingView_exampleString)
        _exampleColor = a.getColor(
                R.styleable.CustomDrawingView_exampleColor,
                exampleColor)
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        _exampleDimension = a.getDimension(
                R.styleable.CustomDrawingView_exampleDimension,
                exampleDimension)

        a.recycle()

        // Set up a default TextPaint object
        textPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        textPaint?.let {
            it.textSize = exampleDimension
            it.color = exampleColor
            textWidth = it.measureText(exampleString)
            textHeight = it.fontMetrics.bottom
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // must redraw all paths with their corresponding paint
        for ((i, path) in paths.withIndex()) {
            canvas.drawPath(path, paints[i])
        }


        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        exampleString?.let {
            // Draw the text.
            canvas.drawText(it,
                    paddingLeft + (contentWidth - textWidth) / 2,
                    paddingTop + (contentHeight + textHeight) / 2,
                    textPaint)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val eventX = event?.x
        val eventY = event?.y

        if (eventX == null || eventY == null) {
            return super.onTouchEvent(event)
        }

//        setDebugText("onTouchEvent " + paths.size)

        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paths[paths.lastIndex].moveTo(eventX, eventY)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                paths[paths.lastIndex].lineTo(eventX, eventY)
                invalidate()
                true
            }
            else -> {
                false
            }
        }

    }

}
