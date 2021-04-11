package com.sun.github__repo_demo.data.model

data class PaginatedEntities<T>(
        /** List of entities included in the current page */
        val data: MutableList<T>?
)
