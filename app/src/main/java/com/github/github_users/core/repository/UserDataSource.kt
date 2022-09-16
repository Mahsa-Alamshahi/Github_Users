package com.github.github_users.core.repository

import com.github.github_users.core.data.User
import com.github.github_users.core.data.UserList

interface UserDataSource {

    suspend fun getAllUsers(): UserList
    suspend fun getUser(url: String): User?
}