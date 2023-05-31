package com.space.quiz.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.space.quiz.R
import com.space.quiz.databinding.CustomTitleViewBinding


class CustomTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: CustomTitleViewBinding =
        CustomTitleViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun collectedPointsToolBar() {
        val drawableIcon = ContextCompat.getDrawable(context, R.drawable.ic_yellow_star)
        drawableIcon?.setBounds(0, 0, drawableIcon.intrinsicWidth, drawableIcon.intrinsicHeight)
        binding.titleTextView.setCompoundDrawables(null, null, drawableIcon, null)
        binding.titleTextView.text = context.getString(R.string.earned_points_text)

        val backDrawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_left)
        binding.backButtonImageView.setImageDrawable(backDrawable)

    }

    fun subjectTitleToolBar() {
        val cancelDrawable = ContextCompat.getDrawable(context, R.drawable.ic_cancel)
        binding.cancelButtonImageView.setImageDrawable(cancelDrawable)
        binding.titleTextView.text = context.getString(R.string.subject_text)
    }

    fun setCancelButton(onClickListener: () -> Unit) {
        binding.cancelButtonImageView.setOnClickListener {
            onClickListener.invoke()
        }
    }

    fun setBackButton(onClickListener: () -> Unit) {
        binding.backButtonImageView.setOnClickListener {
            onClickListener.invoke()
        }
    }
}