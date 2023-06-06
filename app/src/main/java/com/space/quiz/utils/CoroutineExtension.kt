package com.space.quiz.utils

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun ViewModel.viewModelScope(
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch { block() }
}

fun <T> LifecycleOwner.collectInLifecycleScope(
    flow: Flow<T>,
    action: suspend (value: T) -> Unit
): Job {
    return lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect { value ->
                action(value)
            }
        }
    }
}
