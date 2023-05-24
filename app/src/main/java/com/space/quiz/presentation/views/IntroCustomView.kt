package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import com.space.quiz.R

class IntroCustomView(
    context: Context,
    attrs: AttributeSet
) : BaseCustomView(context, attrs) {

    /**the centerX represents the value of x-coordinate for the center
     *it divides width of the view by 2*/
    private val centerX get() = width / 2

    //the cornerRadius represents the value of radius for the corners
    private val cornerRadius get() = width / 2

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
            lineTo(0f, height / 2)
            close()
            canvas.drawPath(path, paint)
        }
    }

    private fun drawLightBlueBackground(canvas: Canvas) {
        /**the heightAdjustment  represents the vertical adjustment applied to the centerY positions of circles and
         *calculates value by substracting one/third of the height from half of the width**/
        val heightAdjustment = (width / 2) - (height / 3)

        /**the centerY1 calculates the centerY1 value by dividing the height by 3 and adding the heightAdjustment value
         *It determines the y-coordinate for the center of the first circle*/
        val centerY1 = height / 3 + heightAdjustment

        /**It determines the y-coordinate for the center of the second circle
         *the centerY2 calculates the centerY2 value by multiplying
         *the height by 2 and dividing it by 3 and then subtracting the heightAdjustment value**/
        val centerY2 = height * 2 / 3 - heightAdjustment

        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_light)
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
            canvas.drawPath(path, paint)
        }
    }
}