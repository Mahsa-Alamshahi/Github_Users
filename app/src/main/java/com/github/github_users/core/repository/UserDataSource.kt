package com.github.github_users.core.repository

import com.github.github_users.core.data.User

interface UserDataSource {

    suspend fun getAllUsers(page: Int): List<User>?
    suspend fun getUser(login: String): User?
    suspend fun searchUser(page: Int, userName: String): List<User>?
}