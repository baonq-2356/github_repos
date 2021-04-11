package com.sun.github__repo_demo.main

import com.sun.data.model.RepoItem

interface RepoItemListener {
    fun onRepoItemClicked(item: RepoItem, position: Int)
}
