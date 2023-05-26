package com.space.quiz.presentation.intro

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.space.quiz.R
import com.space.quiz.databinding.FragmentHomeBinding
import com.space.quiz.databinding.FragmentIntroBinding
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.dialog.CustomDialogView
import com.space.quiz.presentation.home.HomeFragment

class IntroFragment : BaseFragment<FragmentIntroBinding>() {

    override fun inflate(): Inflater<FragmentIntroBinding> {
        return FragmentIntroBinding::inflate
    }

    override fun onBind() {
        binding.introStartQuizButton.setOnClickListener {
            showDialog()
        }
    }
    private fun showDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val customDialogView = CustomDialogView(requireContext())
        customDialogView.showCongratsState()
        alertDialogBuilder.setView(customDialogView)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()

     /*   val positiveButton: TextView = customDialogView.getPositiveButton()
        positiveButton.setOnClickListener {
            //navigation()
            alertDialog.dismiss()
        }
        val negativeButton: TextView = customDialogView.getNegativeButton()
        negativeButton.setOnClickListener {
            alertDialog.dismiss()
        }
*/
        val neutralButton: TextView = customDialogView.getNeutralButton()
        neutralButton.setOnClickListener {
            navigateTo(R.id.action_introFragment_to_homeFragment)
            alertDialog.dismiss()
        }
    }
}