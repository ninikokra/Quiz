package com.space.quiz.presentation.home

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
import com.space.quiz.presentation.base.BaseFragment
import com.space.quiz.presentation.base.Inflater
import com.space.quiz.presentation.dialog.CustomDialogView

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }

    override fun onBind() {
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val customDialogView = CustomDialogView(requireContext())
        customDialogView.showCloseState()
        customDialogView.setBackgroundColor(Color.TRANSPARENT)

        alertDialogBuilder.setView(customDialogView)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()

        val positiveButton: TextView = customDialogView.getPositiveButton()
        positiveButton.setOnClickListener {
            navigateTo(R.id.action_homeFragment_to_introFragment)
            alertDialog.dismiss()
        }
        val negativeButton: TextView = customDialogView.getNegativeButton()
        negativeButton.setOnClickListener {
            alertDialog.dismiss()
        }

        /*  val neutralButton: TextView = customDialogView.getNeutralButton()
          neutralButton.setOnClickListener {
              findNavController().navigate(R.id.action_introFragment_to_homeFragment)
              alertDialog.dismiss()
          }*/
    }
}