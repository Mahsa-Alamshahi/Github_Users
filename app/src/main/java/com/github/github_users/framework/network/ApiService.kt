package com.github.github_users.framework.network

import com.github.github_users.core.data.User
import com.github.github_users.core.data.UserList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("users")
    suspend fun getUserList(@Query("since") since: Int, @Query("per_page") perPage: Int,): Response<List<User>>

    @GET
    suspend fun getUser(@Url url: String): Response<User>


    @GET("users")
    suspend fun searchUser(@Query("since") since: Int, @Query("per_page") perPage: Int,
                            @Query("q") search: String): Response<UserList>

}