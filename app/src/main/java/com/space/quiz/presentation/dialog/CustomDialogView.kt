package com.space.quiz.presentation.dialog

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.space.quiz.R
import com.space.quiz.databinding.CustomDialogBinding

class CustomDialogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: CustomDialogBinding =
        CustomDialogBinding.inflate(LayoutInflater.from(context), this, true)

    fun showCloseState() {
        with(binding) {
            exitQuestionTextVIew.visibility = View.VISIBLE
            yesTextView.visibility = View.VISIBLE
            noTextView.visibility = View.VISIBLE
            exitQuestionTextVIew.text = context.getString(R.string.dialog_quiz_quit_text)
        }
    }

    fun showExitState() {
        with(binding) {
            exitQuestionTextVIew.visibility = View.VISIBLE
            yesTextView.visibility = View.VISIBLE
            noTextView.visibility = View.VISIBLE
            exitQuestionTextVIew.text = context.getString(R.string.dialog_exit_text)
        }
    }

    fun showCongratsState() {
        with(binding) {
            congratsIcon.visibility = View.VISIBLE
            congratsTextView.visibility = View.VISIBLE
            earnedPointsTextView.visibility = View.VISIBLE
            viewLine.visibility = View.VISIBLE
            closeDialogButton.visibility = View.VISIBLE
        }
    }

    fun getPositiveButton(): TextView {
        return binding.yesTextView
    }

    fun getNegativeButton(): TextView {
        return binding.noTextView
    }

    fun getNeutralButton(): TextView {
        return binding.closeDialogButton
    }
}