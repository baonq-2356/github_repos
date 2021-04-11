package com.sun.github__repo_demo.common

import androidx.paging.LoadState

object Constants {
    fun mappingLoadStatus(value: LoadState) = when(value) {
        LoadStates.LOADING.value -> LoadStates.LOADING
        LoadStates.ERROR.value -> LoadStates.ERROR
        else -> LoadStates.NOT_LOADING
    }

    enum class LoadStates(val value: LoadState) {
        LOADING(LoadState.Loading),
        ERROR(LoadState.Error(Throwable())),
        NOT_LOADING(LoadState.NotLoading(true))
    }
}
