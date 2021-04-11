package com.sun.github__repo_demo.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.adapter.RepoPagingAdapter
import com.sun.github__repo_demo.data.model.PaginatedEntities
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val githubRepoApi: GithubRepoApi
) {
    fun getRepos(adapter: RepoPagingAdapter): LiveData<PagingData<RepoItem>> {
        return getPagingResult(adapter) {
            githubRepoApi.getRepos(it)
        }
    }

    private fun getPagingResult(
        adapter: RepoPagingAdapter,
        query: suspend (page: Int) -> List<RepoItem>
    ): LiveData<PagingData<RepoItem>> {
        return Pager(
                config = PagingConfig(
                    pageSize = 1,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { RepoPagingSource(adapter, query) }
        ).liveData
    }
}
