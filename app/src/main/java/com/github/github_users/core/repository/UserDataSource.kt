package com.github.github_users.core.repository

import com.github.github_users.core.data.User

interface UserDataSource {

    suspend fun getAllUsers(page: Int, search: String): List<User>
    suspend fun getUser(login: String): User?
}