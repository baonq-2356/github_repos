package com.sun.github__repo_demo.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<out T : ViewDataBinding> constructor(
    val binding: T
): RecyclerView.ViewHolder(binding.root)
