package com.github.github_users.core.repository

import com.github.github_users.core.data.User
import com.github.github_users.framework.network.ApiService
import javax.inject.Inject


class UserRepository @Inject constructor(var apiService: ApiService) : UserDataSource {


    override suspend fun getAllUsers(): List<User> {
//        var response = ArrayList<User>()
//        CoroutineScope(Dispatchers.IO).launch {
//            response = apiService.getUserList().body()!!
//        }
//       return withContext(Dispatchers.Main) {
//           response
//       }
        var response = apiService.getUserList().body()
        return response!!
    }

    override suspend fun getUser(url: String): User? {
        return User()
    }

}