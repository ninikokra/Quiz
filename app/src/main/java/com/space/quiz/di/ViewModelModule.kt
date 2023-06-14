package com.space.quiz.di

import com.space.quiz.presentation.model.mapper.subject.SubjectDomainUiMapper
import com.space.quiz.presentation.ui_details.vm.DetailsViewModel
import com.space.quiz.presentation.ui_home.vm.HomeViewModel
import com.space.quiz.presentation.ui_intro.vm.IntroViewModel
import com.space.quiz.presentation.ui_questions.vm.QuestionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        IntroViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        HomeViewModel(get(),get(),get(), SubjectDomainUiMapper())
    }
    viewModel {
        DetailsViewModel()
    }
    viewModel {
        QuestionsViewModel()
    }
}