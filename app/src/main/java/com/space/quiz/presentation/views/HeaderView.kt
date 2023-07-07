package com.space.quiz.presentation.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.findNavController
import com.space.quiz.R
import com.space.quiz.databinding.CustomTitleViewBinding

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CustomTitleViewBinding =
        CustomTitleViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var actionType: Actions? = null
        set(value) {
            value?.let {
                determineActionsType(value)
            }
            field = value
        }

    var customText: String? = null
        set(value) {
            binding.titleTextView.text = value
            field = value
        }

    private var textDrawable: Drawable? = null
        set(value) {
            field = value
            value?.let {
                collectedPointsToolBar()
            }
        }

    init {
        val typeArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HeaderView,
            defStyleAttr,
            0
        )

        actionType = Actions.values()[typeArray.getInt(R.styleable.HeaderView_actions, 0)]
        customText = typeArray.getString(R.styleable.HeaderView_text)
        textDrawable = typeArray.getDrawable(R.styleable.HeaderView_textDrawable)


        binding.backButtonImageView.setOnClickListener {
            findNavController().navigateUp()
        }
        typeArray.recycle()
    }

    fun setCancelButton(onClickListener: () -> Unit) {
        binding.cancelButtonImageView.setOnClickListener {
            onClickListener.invoke()
        }
    }

    private fun collectedPointsToolBar() {
        with(binding) {
            val drawableIcon = ContextCompat.getDrawable(context, R.drawable.ic_yellow_star)
            drawableIcon?.setBounds(0, 0, drawableIcon.intrinsicWidth, drawableIcon.intrinsicHeight)
            titleTextView.setCompoundDrawables(null, null, drawableIcon, null)
        }
    }

    private fun determineActionsType(actions: Actions) {
        when (actions) {
            Actions.BACK -> {
                val backDrawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_left)
                binding.backButtonImageView.setImageDrawable(backDrawable)
            }
            Actions.CANCEL -> {
                val cancelDrawable = ContextCompat.getDrawable(context, R.drawable.ic_cancel)
                binding.cancelButtonImageView.setImageDrawable(cancelDrawable)
            }
            else -> {}

        }
    }

    enum class Actions {
        NOTHING,
        BACK,
        CANCEL,
        BOTH
    }
}