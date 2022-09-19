package com.github.github_users.core.repository

import com.github.github_users.core.data.User
import com.github.github_users.framework.AppConstant
import com.github.github_users.framework.network.ApiService
import javax.inject.Inject


class UserRepository @Inject constructor(var apiService: ApiService) : UserDataSource {

    override suspend fun getAllUsers(page: Int, search: String): List<User>? =
        apiService.getUserList(page, AppConstant.PAGE_SIZE).body()


    override suspend fun getUser(login: String): User? =
        apiService.getUser("users/$login").body()

}