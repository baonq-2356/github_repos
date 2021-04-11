package com.sun.github__repo_demo.data.remote

import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.data.model.PaginatedEntities
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepoApi {
    @GET("orgs/framgia/repos")
    suspend fun getRepos(
            @Query("page") page: Int
    ): List<RepoItem>
}
