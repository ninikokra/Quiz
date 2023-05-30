package com.space.quiz.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
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
    }

    fun showDialog() {
        alertDialog.show()
    }

    fun showCloseState() {
        with(binding) {
            congratsGroup.isVisible(true)
            exitQuestionTextVIew.text = context.getString(R.string.dialog_quiz_quit_text)
        }
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

    fun setPositiveButtonClickListener(onClickListener: () -> Unit) {
        binding.yesTextView.setOnClickListener {
            onClickListener.invoke()
            alertDialog.dismiss()
        }
    }

    fun setNegativeButtonClickListener(onClickListener: () -> Unit) {
        binding.noTextView.setOnClickListener {
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