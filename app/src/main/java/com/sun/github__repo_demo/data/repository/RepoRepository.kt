package com.sun.github__repo_demo.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.adapter.RepoPagingAdapter

interface RepoRepository {
    fun getRepos(adapter: RepoPagingAdapter): LiveData<PagingData<RepoItem>>
}
