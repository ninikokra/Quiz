package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

abstract class BaseCustomView(
    context: Context,
    attrs: AttributeSet?
) : View(context, attrs) {

    protected val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    protected val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCustomView(canvas)
    }


    protected val width get() = getWidth().toFloat()
    protected val height get() = getHeight().toFloat()

    protected open fun drawCustomView(canvas: Canvas) {
    }
}