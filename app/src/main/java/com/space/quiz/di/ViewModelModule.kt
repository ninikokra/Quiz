package com.space.quiz.di

import com.space.quiz.presentation.fragment_details.vm.DetailsViewModel
import com.space.quiz.presentation.fragment_home.vm.HomeViewModel
import com.space.quiz.presentation.fragment_intro.vm.IntroViewModel
import com.space.quiz.presentation.fragment_questions.vm.QuestionsViewModel
import com.space.quiz.presentation.model.mapper.UserPresentationToDomainMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        IntroViewModel(get(), UserPresentationToDomainMapper())
    }
    viewModel{
        HomeViewModel()
    }
    viewModel{
        DetailsViewModel()
    }
    viewModel{
        QuestionsViewModel()
    }
}