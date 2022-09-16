package com.github.github_users.framework.network

import com.github.github_users.core.data.User
import com.github.github_users.core.data.UserList
import com.github.github_users.core.repository.UserDataSource
import javax.inject.Inject


class NetworkUserDataSource(var apiService: ApiService) : UserDataSource {


    override suspend fun getAllUsers(): UserList {
        var response = apiService.getUserList().body()
        return response!!
    }


    override suspend fun getUser(url: String): User? {
        TODO("Not yet implemented")
    }
}