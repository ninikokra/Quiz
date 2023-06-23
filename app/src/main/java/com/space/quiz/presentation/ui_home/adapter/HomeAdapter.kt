package com.space.quiz.presentation.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.quiz.databinding.QuizSubjectItemsBinding
import com.space.quiz.presentation.model.SubjectUIModel
import com.space.quiz.utils.DiffCallback
import com.space.quiz.utils.setImage

class HomeAdapter : ListAdapter<SubjectUIModel, HomeAdapter.QuizSubjectViewHolder>(DiffCallback()) {

    private var itemClickListener: ((SubjectUIModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizSubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuizSubjectItemsBinding.inflate(inflater, parent, false)
        return QuizSubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizSubjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }

    fun setOnItemClickListener(listener: (SubjectUIModel) -> Unit) {
        itemClickListener = listener
    }

    class QuizSubjectViewHolder(private val binding: QuizSubjectItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SubjectUIModel) {
            binding.subjectTitleTextView.text = item.quizTitle
            binding.subjectDescriptionTextView.text = item.quizDescription
            binding.subjectIconImageView.setImage(item.quizIcon)
        }
    }
}