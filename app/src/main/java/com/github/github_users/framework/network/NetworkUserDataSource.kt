package com.github.github_users.framework.network

import com.github.github_users.core.data.User
import com.github.github_users.core.repository.UserDataSource


class NetworkUserDataSource(var apiService: ApiService) : UserDataSource {


    override suspend fun getAllUsers(): List<User> =
         apiService.getUserList().body()!!




    override suspend fun getUser(url: String): User? {
        TODO("Not yet implemented")
    }
}