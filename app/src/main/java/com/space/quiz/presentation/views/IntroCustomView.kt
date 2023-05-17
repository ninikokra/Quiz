package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.space.quiz.R

class IntroCustomView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    private val fillPaint = Paint().apply {
        style = Paint.Style.FILL
    }
    private val path = Path()
    private val width get() = getWidth().toFloat()
    private val height get() = getHeight().toFloat()
    private val centerX get() = width / 2
    private val cornerRadius get() = width / 2

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path.apply {
            reset()
            fillPaint.color = context.getColor(R.color.blue_secondary_default)
            moveTo(0f, 0f)
            lineTo(centerX, 0f)
            lineTo(0f, height / 2)
            close()
            canvas.drawPath(path, fillPaint)
        }
        val heightAdjustment = (width / 2) - (height / 3)
        val centerY1 = height / 3 + heightAdjustment
        val centerY2 = height * 2 / 3 - heightAdjustment
        path.apply {
            reset()
            fillPaint.color = context.getColor(R.color.blue_rectangle)
            addCircle(centerX, centerY1, cornerRadius, Path.Direction.CW)
            addCircle(centerX, centerY2, cornerRadius, Path.Direction.CW)
            moveTo(centerX, 0f)
            lineTo(width, 0f)
            lineTo(width, centerY2)
            lineTo(centerX, height)
            lineTo(0f, height)
            lineTo(0f, centerY1)
            lineTo(centerX, 0f)
            close()
            canvas.drawPath(path, fillPaint)
        }
    }
}