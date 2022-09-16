package com.github.github_users.core.repository

import com.github.github_users.core.data.User

interface UserDataSource {

    suspend fun getAllUsers(): List<User>
    suspend fun getUser(url: String): User?
}