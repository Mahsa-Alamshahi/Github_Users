package com.github.github_users.core.repository



class UserRepository(private val dataSource: UserDataSource) {

    suspend fun getUser(url: String) = dataSource.getUser(url)
    suspend fun getAllUser() = dataSource.getAllUsers()
}