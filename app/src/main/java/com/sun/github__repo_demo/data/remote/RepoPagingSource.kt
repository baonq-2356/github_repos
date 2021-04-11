package com.sun.github__repo_demo.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.adapter.RepoPagingAdapter
import com.sun.github__repo_demo.data.model.PaginatedEntities

private const val INITIALIZE_PREV_KEY = 1

class RepoPagingSource(
    private val adapter: RepoPagingAdapter,
    private val query: suspend (page: Int) -> List<RepoItem>
): PagingSource<Int, RepoItem>() {

    private val cachingData = mutableListOf<RepoItem>()

    override fun getRefreshKey(state: PagingState<Int, RepoItem>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoItem> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: INITIALIZE_PREV_KEY

        // Load data from caching
        if (adapter.cachingData.isNotEmpty()) {
            cachingData.clear()
            cachingData.addAll(adapter.cachingData)
            return LoadResult.Page(
                data = cachingData,
                prevKey = if (nextPageNumber == INITIALIZE_PREV_KEY) null else nextPageNumber.minus(1),
                nextKey = if (cachingData.isNullOrEmpty()) null else nextPageNumber.plus(1)
            )
        }

        // Load data from source
        return try {
            val response = query(nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == INITIALIZE_PREV_KEY) null else nextPageNumber.minus(1),
                nextKey = if (response.isNullOrEmpty()) null else nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean = true
}
