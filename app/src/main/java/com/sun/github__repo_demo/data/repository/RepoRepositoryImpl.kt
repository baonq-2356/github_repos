package com.sun.github__repo_demo.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.adapter.RepoPagingAdapter
import com.sun.github__repo_demo.data.remote.GithubRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource
): RepoRepository {
    override fun getRepos(adapter: RepoPagingAdapter): LiveData<PagingData<RepoItem>> {
        return githubRemoteDataSource.getRepos(adapter)
    }
}
