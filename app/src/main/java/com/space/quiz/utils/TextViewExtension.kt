package com.space.quiz.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView

/*
fun AppCompatTextView.setColoredText(originalText: String, targetText: String, color: Int) {
    val spannableString = SpannableString(originalText)
    val startIndex = originalText.indexOf(targetText)
    val endIndex = startIndex + targetText.length

    if (startIndex >= 0 && endIndex <= originalText.length) {
        spannableString.setSpan(
            ForegroundColorSpan(color),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    text = spannableString
}*/
fun AppCompatTextView.setColoredText(originalText: String, targetText: String, @ColorInt color: Int) {
    val spannableString = SpannableString("$originalText$targetText")
    val colorSpan = ForegroundColorSpan(color)
    val styleSpan = StyleSpan(Typeface.BOLD)

    spannableString.setSpan(
        colorSpan,
        originalText.length,
        originalText.length + targetText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    spannableString.setSpan(
        styleSpan,
        originalText.length,
        originalText.length + targetText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.text = spannableString
}
