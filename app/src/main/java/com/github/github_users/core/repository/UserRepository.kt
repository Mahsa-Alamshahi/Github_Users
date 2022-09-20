package com.github.github_users.core.repository

import com.github.github_users.core.data.User
import com.github.github_users.framework.AppConstant
import com.github.github_users.framework.network.ApiService
import javax.inject.Inject


class UserRepository @Inject constructor(var apiService: ApiService) : UserDataSource {

    override suspend fun getAllUsers(page: Int): List<User>? =
        apiService.getUserList(page).body()


    override suspend fun getUser(login: String): User? =
        apiService.getUser("users/$login").body()


    override suspend fun searchUser(page: Int, userName: String): List<User>? =
        apiService.searchUser(page, AppConstant.PAGE_SIZE, userName).body()

}