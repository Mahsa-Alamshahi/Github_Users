package com.github.github_users.framework.viewmodel

import androidx.lifecycle.ViewModel
import com.github.github_users.core.data.User
import com.github.github_users.core.usecase.GetUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(var getUserUsecase: GetUserUsecase): ViewModel() {


    fun getUser(login: String): Flow<User?> {
        return flow {
            emit(getUserUsecase.getUser(login))
        }.flowOn(Dispatchers.IO)
    }
}