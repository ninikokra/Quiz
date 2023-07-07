package com.space.quiz.presentation.views.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.space.quiz.R
import com.space.quiz.databinding.CustomDialogBinding
import com.space.quiz.utils.isVisible

class CustomDialogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: CustomDialogBinding =
        CustomDialogBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var alertDialog: AlertDialog

    init {
        setupDialog()
    }

    private fun setupDialog() {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(this)

        alertDialog = alertDialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
    }

    fun showDialog() {
        alertDialog.show()
    }


    fun showCloseState() {
        with(binding) {
            exitCloseGroup.isVisible(true)
            exitQuestionTextVIew.text = context.getString(R.string.dialog_quiz_quit_text)
        }
    }
    fun setText(pointText: String){
        binding.earnedPointsTextView.text = pointText
    }

    fun showExitState() {
        with(binding) {
            exitCloseGroup.isVisible(true)
            exitQuestionTextVIew.text = context.getString(R.string.dialog_exit_text)
        }
    }

    fun showCongratsState() {
        binding.congratsGroup.isVisible(true)
    }

    fun showFailedState(){
        with(binding){
            congratsGroup.isVisible(true)
            congratsIcon.text = context.getString(R.string.failed_icon_text)
            congratsTextView.text = ""
        }
    }

    fun setPositiveButtonClickListener(onClickListener: () -> Unit) {
        binding.yesButton.setOnClickListener {
            onClickListener.invoke()
            alertDialog.dismiss()
        }
    }

    fun setNegativeButtonClickListener(onClickListener: () -> Unit) {
        binding.noButton.setOnClickListener {
            onClickListener.invoke()
            alertDialog.dismiss()
        }
    }

    fun setNeutralButtonClickListener(onClickListener: () -> Unit) {
        binding.closeDialogButton.setOnClickListener {
            onClickListener.invoke()
            alertDialog.dismiss()
        }
    }
}