package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import com.space.quiz.R

class QuestionsTopView(
    context: Context,
    attrs: AttributeSet
) : BaseCustomView(context, attrs) {

    override fun drawCustomView(canvas: Canvas) {

        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_lightest)
            moveTo(0f, 0f)
            lineTo(width, 0f)
            arcTo(
                RectF(width / 2, 0f,width, height),
                0f, 90f
            )
            arcTo(
                RectF(0f, 0f, width / 2, height),
                90f, 90f
            )
            close()
        }
        canvas.drawPath(path, paint)
    }
}