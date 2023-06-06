package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.space.quiz.R

class IntroCustomView(
    context: Context,
    attrs: AttributeSet
) : BaseCustomView(context, attrs) {

    /**the centerX represents the value of x-coordinate for the center
     *it divides width of the view by 2*/
    private val centerX get() = width / 2

    override fun drawCustomView(canvas: Canvas) {
        drawDarkBlueBackground(canvas)
        drawLightBlueBackground(canvas)
    }

    private fun drawDarkBlueBackground(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_default)
            moveTo(0f, 0f)
            lineTo(centerX, 0f)
            lineTo(0f, height)
            close()
            canvas.drawPath(path, paint)
        }
    }

    private fun drawLightBlueBackground(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_light)
            moveTo(width, 0f)
            arcTo(
                RectF(0f, 0f, width, height),
                0f, 90f
            )
            lineTo(0f, height)
            arcTo(
                RectF(0f, 0f, width, height),
                180f, 90f
            )
            close()
        }
        canvas.drawPath(path, paint)
    }
}