package com.github.github_users.core.data

import com.google.gson.annotations.SerializedName



data class UserList(
	@field:SerializedName("UserList")
	val userList: List<User?>? = null
)


