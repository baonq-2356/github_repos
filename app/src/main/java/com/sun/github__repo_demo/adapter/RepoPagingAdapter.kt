package com.sun.github__repo_demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sun.data.model.RepoItem
import com.sun.github__repo_demo.BR
import com.sun.github__repo_demo.R
import com.sun.github__repo_demo.base.BaseViewHolder
import com.sun.github__repo_demo.main.RepoItemListener

class RepoPagingAdapter(
    private val listener: RepoItemListener
): PagingDataAdapter<RepoItem, BaseViewHolder<ViewDataBinding>>(RepoComparator) {

    val cachingData = mutableListOf<RepoItem>()

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        val item = getItem(position)
        holder.binding.apply {
            setVariable(BR.item, item)
            setVariable(BR.position, position)
            setVariable(BR.listener, listener)
        }
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_repo,
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    fun updateItem(item: RepoItem, position: Int) {
        cachingData.clear()
        cachingData.addAll(snapshot().items)
//        if (position > 0) {
            cachingData[position] = item
            notifyItemChanged(position)
            refresh()
//        }
    }

}

object RepoComparator : DiffUtil.ItemCallback<RepoItem>() {
    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem == newItem
    }
}
