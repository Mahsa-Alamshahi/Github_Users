package com.github.github_users.core.repository

import com.github.github_users.core.data.User
import com.github.github_users.framework.network.ApiService
import com.orhanobut.logger.Logger
import javax.inject.Inject


class UserRepository @Inject constructor(var apiService: ApiService): UserDataSource{

    override suspend fun getAllUsers(page: Int, search: String): List<User> {
        var response = apiService.getUserList(page, 50, ).body()
        Logger.d(response?.get(1)?.avatarUrl)
        return response!!
    }

    override suspend fun getUser(login: String): User {
        var response = apiService.getUser("users/$login").body()
        return response!!
    }

}