package com.space.quiz.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.space.quiz.R
import com.space.quiz.databinding.AnswerItemBinding
import com.space.quiz.utils.isVisible

class AnswersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: AnswerItemBinding =
        AnswerItemBinding.inflate(LayoutInflater.from(context), this, true)


    fun setCorrectWithoutIcon() {
        with(binding) {
            questionsRectangleView.setRectangleBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.success
                )
            )
            answersTextView.setTextColor(ContextCompat.getColor(context, R.color.neutral_04_white))
            pointTokenTextview.isVisible(false)
        }
    }

    fun setDefaultAnswer() {
        with(binding) {
            questionsRectangleView.setRectangleBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.neutral_04_light_grey
                )
            )
            answersTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.neutral_01_dark_grey
                )
            )
            pointTokenTextview.isVisible(false)

        }
    }

    private fun setCorrectAnswer() {
        with(binding) {
            questionsRectangleView.setRectangleBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.success
                )
            )
            answersTextView.setTextColor(ContextCompat.getColor(context, R.color.neutral_04_white))
            pointTokenTextview.isVisible(true)
        }
    }

    private fun setIncorrectAnswer() {
        with(binding) {
            questionsRectangleView.setRectangleBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.wrong
                )
            )
            answersTextView.setTextColor(ContextCompat.getColor(context, R.color.neutral_04_white))
        }
    }

    fun setAnswers(answers: String) {
        binding.answersTextView.text = answers
    }

    fun checkCorrectAnswer(correctAnswer: String) {
        if (binding.answersTextView.text == correctAnswer) {
            setCorrectAnswer()
        } else setIncorrectAnswer()
    }

}