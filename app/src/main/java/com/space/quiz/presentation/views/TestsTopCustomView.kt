package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import com.space.quiz.R

class TestsTopCustomView(
    context: Context,
    attrs: AttributeSet
) : BaseCustomView(context, attrs) {

    override fun drawCustomView(canvas: Canvas) {
        /**the centerX represents the value of x-coordinate for the center
         *it divides width of the view by 4*/
        val centerX1 = width / 4

        /**the centerX represents the value of x-coordinate for the center
         *multiplying the width of the view by 3 and then dividing it by 4.*/
        val centerX2 = width * 3 / 4
        //represents the radius of the circles in the custom view
        val radius = width / 4

        /**the centerY represents the y-coordinate for the center of the circles.
        It is obtained by subtracting the radius value from the height of the view*/
        val centerY = height - radius

        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_lighter)
            addCircle(centerX1, centerY, radius, Path.Direction.CW)
            addCircle(centerX2, centerY, radius, Path.Direction.CW)

            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, centerY)
            lineTo(centerX2, height)

            lineTo(centerX1, height)
            lineTo(0f, centerY)
            lineTo(0f, 0f)
            close()
        }
        canvas.drawPath(path, paint)
    }
}