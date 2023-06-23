package com.space.quiz.presentation.ui_questions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.quiz.databinding.QuestionsItemBinding

class AnswersAdapter :
    RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder>() {

    private var answers: List<String> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionsItemBinding.inflate(inflater, parent, false)
        return AnswersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        val answer = answers[position]
        holder.bind(answer)
    }

    fun submitList(answersList : List<String>){
        answers = answersList

    }

    override fun getItemCount(): Int {
        return answers.size
    }

    class AnswersViewHolder(private val binding: QuestionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answers: String) {
            binding.answersTextView.text = answers

        }
    }
}