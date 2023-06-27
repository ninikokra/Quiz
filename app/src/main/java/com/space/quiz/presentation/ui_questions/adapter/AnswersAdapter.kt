package com.space.quiz.presentation.ui_questions.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.quiz.databinding.AnswerCustomviewBinding

/*
class AnswersAdapter :
    RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder>() {

    private lateinit var correctAnswer: String

    private var answers: List<String> = emptyList()

    fun submitCorrectAnswer(correctAnswer: String) {
        this.correctAnswer = correctAnswer
    }

    fun submitList(answersList: List<String>) {
        answers = answersList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnswerCustomviewBinding.inflate(inflater, parent, false)
        return AnswersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        val answer = answers[position]
        holder.bind(answer, correctAnswer)
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    class AnswersViewHolder(private val binding: AnswerCustomviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: String, correctAnswer: String) {
            with(binding) {
                answerView.setAnswers(answer)
                answerView.setDefaultAnswer()
                binding.root.setOnClickListener {
                    answerView.checkCorrectAnswer(correctAnswer)
                }
            }
        }
    }
}*/

class AnswersAdapter : RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder>() {
    private var correctAnswer: String = ""
    private var clickedPosition: Int? = null
    private var wrongAnswerClicked: Boolean = false
    private var answers: List<String> = emptyList()

    fun submitCorrectAnswer(correctAnswer: String) {
        this.correctAnswer = correctAnswer
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

    override fun onBindViewHolder(holder: AnswersViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val answer = answers[position]
        val isClickable = clickedPosition == null
        holder.bind(answer, correctAnswer, isClickable, clickedPosition)

        holder.itemView.setOnClickListener {
            if (isClickable) {
                clickedPosition = position
                wrongAnswerClicked = answer != correctAnswer
                notifyItemRangeChanged(0,answer.length)
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