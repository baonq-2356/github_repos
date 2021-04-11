package com.sun.github__repo_demo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.adapter.RepoPagingAdapter
import com.sun.github__repo_demo.base.BaseViewModel
import com.sun.github__repo_demo.data.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoRepository: RepoRepository
): BaseViewModel() {

    fun getRepos(adapter: RepoPagingAdapter): LiveData<PagingData<RepoItem>> {
        return repoRepository.getRepos(adapter).cachedIn(viewModelScope)
    }
}
