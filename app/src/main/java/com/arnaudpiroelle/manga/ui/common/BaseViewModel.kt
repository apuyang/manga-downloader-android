package com.arnaudpiroelle.manga.ui.common

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<A : BaseAction, S : BaseState>(private val initialState: S) : ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    private val internalState = MutableLiveData<S>().apply { value = initialState }

    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    val state = internalState as LiveData<S>

    protected abstract suspend fun onHandle(action: A)

    fun handle(action: A) = launch {
        onHandle(action)
    }

    override fun onCleared() {
        coroutineContext.cancelChildren()
    }

    @MainThread
    protected fun updateState(reducer: (S) -> S) = launch(Dispatchers.Main){
        val currentState = state.value ?: initialState

        internalState.value = reducer(currentState)
    }
}

interface BaseAction
interface BaseState