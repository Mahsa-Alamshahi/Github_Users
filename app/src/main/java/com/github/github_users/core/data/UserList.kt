package com.github.github_users.core.data

import com.google.gson.annotations.SerializedName

data class UserList(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<User>? = null


)
