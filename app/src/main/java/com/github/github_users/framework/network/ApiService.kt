package com.github.github_users.framework.network

import com.github.github_users.core.data.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUserList(): Response<List<User>>

}