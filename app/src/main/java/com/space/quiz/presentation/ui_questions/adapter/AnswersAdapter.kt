package com.space.quiz.presentation.ui_questions.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.quiz.databinding.AnswerCustomviewBinding

class AnswersAdapter : RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder>() {
    private var correctAnswer: String = ""
    private var clickedPosition: Int? = null
    private var wrongAnswerClicked: Boolean = false
    private var answers: List<String> = emptyList()
    private var selectedAnswer: String? = null

    fun submitCorrectAnswer(correctAnswer: String) {
        this.correctAnswer = correctAnswer
    }
    fun getSelectedAnswer(): String? {
        return selectedAnswer
    }
    fun getCorrectAnswer(): String {
        return correctAnswer
    }
    fun isAnswered(): Boolean{
        return clickedPosition != null
    }

    fun submitList(answersList: List<String>) {
        answers = answersList
        clickedPosition = null
        wrongAnswerClicked = false
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnswerCustomviewBinding.inflate(inflater, parent, false)
        return AnswersViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: AnswersViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val answer = answers[position]
        val isClickable = clickedPosition == null
        holder.bind(answer, correctAnswer, isClickable, clickedPosition)

        holder.itemView.setOnClickListener {
            if (isClickable) {
                clickedPosition = position
                wrongAnswerClicked = answer != correctAnswer
                selectedAnswer = answer
                //notifyItemRangeChanged(0, answer.length)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = answers.size

    class AnswersViewHolder(private val binding: AnswerCustomviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            answer: String,
            correctAnswer: String,
            isClickable: Boolean,
            clickedPosition: Int?
        ) {
            with(binding) {
                answerView.setAnswers(answer)
                when {
                    adapterPosition == clickedPosition -> {
                        answerView.checkCorrectAnswer(correctAnswer)
                    }
                    answer == correctAnswer -> {
                        if (isClickable) {
                            answerView.setDefaultAnswer()
                        } else {
                            answerView.setCorrectWithoutIcon()
                        }
                    }
                    else -> answerView.setDefaultAnswer()
                }
                itemView.isClickable = isClickable
            }
        }
    }
}