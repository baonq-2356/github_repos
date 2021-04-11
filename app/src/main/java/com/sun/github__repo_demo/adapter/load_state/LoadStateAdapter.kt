package com.sun.github__repo_demo.adapter.load_state

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.sun.github__repo_demo.BR
import com.sun.github__repo_demo.R
import com.sun.github__repo_demo.base.BaseViewHolder
import com.sun.github__repo_demo.common.Constants
import com.sun.mooos.ui.adapter.LoadStateListener

class LoadStateAdapter(
    private val listener: LoadStateListener
): LoadStateAdapter<LoadStateViewHolder<ViewDataBinding>>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder<ViewDataBinding>, loadState: LoadState) {
        holder.binding.apply {
            Constants.mappingLoadStatus(loadState)
            when (loadState) {
                is LoadState.Loading -> {
                    setVariable(BR.loadState, Constants.LoadStates.LOADING)
                }
                is LoadState.Error -> {
//                    if (loadState.error is java.util.NoSuchElementException) {
//                        setVariable(BR.loadState, Constants.LoadStates.NOT_LOADING)
//                    } else {
                        setVariable(BR.loadState, Constants.LoadStates.ERROR)
//                    }
                }
                else -> {
                    setVariable(BR.loadState, Constants.LoadStates.NOT_LOADING)
                }
            }
            setVariable(BR.listener, listener)
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder<ViewDataBinding> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.layout_load_state, parent, false)
        return LoadStateViewHolder(binding)
    }
}

class LoadStateViewHolder<out T: ViewDataBinding>(bd: T): BaseViewHolder<T>(bd)
