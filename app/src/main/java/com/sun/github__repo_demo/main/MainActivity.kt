package com.sun.github__repo_demo.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.*
import com.sun.github__repo_demo.adapter.RepoComparator
import com.sun.github__repo_demo.adapter.RepoPagingAdapter
import com.sun.github__repo_demo.adapter.load_state.LoadStateAdapter
import com.sun.github__repo_demo.base.BaseActivity
import com.sun.github__repo_demo.databinding.ActivityMainBinding
import com.sun.mooos.ui.adapter.LoadStateListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), RepoItemListener,
    LoadStateListener {
    override val viewModel: MainViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.activity_main

    private val repoAdapter by lazy {
        RepoPagingAdapter(this@MainActivity)
    }

    private val adapter by lazy {
        repoAdapter.withLoadStateFooter(LoadStateAdapter(this@MainActivity))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.adapter = adapter
        viewModel.getRepos(repoAdapter).observe(this) {
            viewModel.executeTask {
                repoAdapter.submitData(it)
                viewBinding.layoutSwipeRefresh.isRefreshing = false
            }
        }
        refreshData()
    }

    override fun onRepoItemClicked(item: RepoItem, position: Int) {
//        repoAdapter.snapshot().filter { data -> data?.id == item.id }.getOrNull(0)?.let {
            item.title += " ------ Clicked"
            repoAdapter.updateItem(item, position)
//        }
    }

    override fun onRetryLoadData() {
        repoAdapter.retry()
    }

    private fun refreshData() {
        viewBinding.layoutSwipeRefresh.apply {
            setOnRefreshListener {
                context?.let {
                    repoAdapter.refresh()
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
