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

/**
 * TODO: document your custom view class.
 */
class CustomDrawingView : View {

    private var _exampleString: String? = null
    private var _exampleColor: Int = Color.RED
    private var _exampleDimension: Float = 0f

    private var textPaint: TextPaint? = null
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

    private var paint = Paint()
    private var paths = mutableListOf<Path>()

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
    var exampleDrawable: Drawable? = null

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
        paint.isAntiAlias = true
        paint.strokeWidth = 20f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paths.add(Path())
    }

    fun setPaintColor(alpha: Int, red: Int, green: Int, blue: Int) {
        paint.color = Color.argb(alpha, red, green, blue)
        paths.add(Path())
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

        if (a.hasValue(R.styleable.CustomDrawingView_exampleDrawable)) {
            exampleDrawable = a.getDrawable(
                    R.styleable.CustomDrawingView_exampleDrawable)
            exampleDrawable?.callback = this
        }

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

//        if (paths.size == 0) {
//            paths.add(Path())
//        }
        canvas.drawPath(paths[paths.lastIndex], paint)


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

        // Draw the example drawable on top of the text.
        exampleDrawable?.let {
            it.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight)
            it.draw(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var eventX = event?.x
        var eventY = event?.y

        if (eventX == null || eventY == null) {
            return super.onTouchEvent(event)
        }

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                paths[paths.lastIndex].moveTo(eventX, eventY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                paths[paths.lastIndex].lineTo(eventX, eventY)
                invalidate()
                return true
            }
            else -> {
                return false
            }
        }

    }
}
