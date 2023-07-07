package com.space.quiz.presentation.ui_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.quiz.databinding.QuizSubjectItemsBinding
import com.space.quiz.presentation.model.mapper.UserDetailsUIModel
import com.space.quiz.utils.DiffCallback
import com.space.quiz.utils.isVisible
import com.space.quiz.utils.setImage

class DetailsAdapter :
    ListAdapter<UserDetailsUIModel, DetailsAdapter.UserDetailsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuizSubjectItemsBinding.inflate(inflater, parent, false)
        return UserDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class UserDetailsViewHolder(private val binding: QuizSubjectItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserDetailsUIModel) {
            with(binding) {
                subjectTitleTextView.text = item.quizTitle
                subjectDescriptionTextView.text = item.quizDescription
                subjectIconImageView.setImage(item.quizIcon)
                pointsBySubjectTextVIew.isVisible(true)
                pointsBySubjectTextVIew.text = item.collectedPoints.toString()
            }
        }
    }
}