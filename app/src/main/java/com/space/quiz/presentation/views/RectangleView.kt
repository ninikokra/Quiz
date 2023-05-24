package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.space.quiz.R

class RectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseCustomView(context, attrs) {

    private var rectangleBackgroundColor: Int = Color.BLUE

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectangleView)
        rectangleBackgroundColor =
            typedArray.getColor(R.styleable.RectangleView_rectangleBackgroundColor, Color.BLUE)
        typedArray.recycle()
        paint.color = rectangleBackgroundColor
        paint.style = Paint.Style.FILL
    }

    override fun drawCustomView(canvas: Canvas) {
        val width = width.toFloat()
        val height = height.toFloat()
        val radius = width.coerceAtMost(height) / 3
        canvas.drawRoundRect(0f, 5f, width, height, radius, radius, paint)
    }
}