package com.sun.data.model

import com.google.gson.annotations.SerializedName
import com.sun.github__repo_demo.data.model.BaseModel

/**
 * Sample object
 */
data class RepoItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var title: String
) : BaseModel()
