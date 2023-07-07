package com.space.quiz.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quiz.R
import com.space.quiz.databinding.ProgressViewBinding

class ProgressView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ProgressViewBinding=
        ProgressViewBinding.inflate(LayoutInflater.from(context),this,true)
    private var maxValue = 0
    private var currentValue = 0
    private var correctAnswersValue = 0

    fun setMaxSize(maxValue: Int) {
        this.maxValue = maxValue
        binding.answersProgress.max = maxValue
        setContent()
    }

    fun setProgress(index: Int) {
        currentValue = index
        setContent()
    }

    fun setPoints(pointsValue: Int) {
        this.correctAnswersValue = pointsValue
        setContent()
    }

    private fun setContent() {
        with(binding) {
            val progress = "$currentValue/$maxValue"
            progressBarPoints.text = progress
            currentAnswersPoint.text = context.getString(R.string.current_point_text, correctAnswersValue)
            binding.answersProgress.setProgress(currentValue, true)
        }
    }
}