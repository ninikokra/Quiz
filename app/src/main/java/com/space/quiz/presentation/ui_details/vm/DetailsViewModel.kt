package com.space.quiz.presentation.ui_details.vm

import androidx.navigation.NavDirections
import com.space.quiz.domain.usecase.datastore.clear.ClearDatastoreUseCase
import com.space.quiz.domain.usecase.datastore.read.ReadDatastoreUseCase
import com.space.quiz.domain.usecase.user_points.GetUserPointsUseCase
import com.space.quiz.presentation.base.BaseViewModel
import com.space.quiz.presentation.model.mapper.UserDetailsUIModel
import com.space.quiz.presentation.model.mapper.details.UserDetailsDomainUIMapper
import com.space.quiz.presentation.model.mapper.details.UserDetailsUIDomainMapper
import com.space.quiz.presentation.ui_details.ui.DetailsFragment
import com.space.quiz.presentation.ui_details.ui.DetailsFragmentDirections
import com.space.quiz.presentation.ui_home.ui.HomeFragmentDirections
import com.space.quiz.utils.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel(
    private val getUserDataStoreUseCase: ReadDatastoreUseCase,
    private val getUserPointsUseCase: GetUserPointsUseCase,
    private val userDetailsUIDomainMapper: UserDetailsDomainUIMapper,
    private val clearDatastoreUseCase: ClearDatastoreUseCase,

    ) : BaseViewModel() {

    private val _subjectsItem = MutableStateFlow<List<UserDetailsUIModel>>(emptyList())
    val subjectsItem: StateFlow<List<UserDetailsUIModel>> = _subjectsItem

    private var currentUser: String? = null

    fun getCurrentUser() {
        viewModelScope {
            val result = getUserDataStoreUseCase.invoke()
            val currentUser = result.getOrNull()
            if (currentUser != null) {
                this@DetailsViewModel.currentUser = currentUser
            }
        }
    }
    fun getUserSubject() {
        viewModelScope {
            val userSubjects = getUserPointsUseCase.invoke(currentUser)
            val userSubjectUIModels = userSubjects.map { userDetailsUIDomainMapper(it) }
            _subjectsItem.value = userSubjectUIModels
        }
    }
    fun logout() {
        viewModelScope {
            clearDatastoreUseCase.invoke()
            navigateToIntro()
        }
    }

    private fun navigateToIntro(){
        navigate(DetailsFragmentDirections.actionDetailsFragmentToIntroFragment())
    }
     fun navigateToHome(){
        navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
    }

}
