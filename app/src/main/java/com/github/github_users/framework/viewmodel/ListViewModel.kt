package com.github.github_users.framework.viewmodel

import androidx.lifecycle.ViewModel
import com.github.github_users.core.data.User
import com.github.github_users.core.usecase.GetUserListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(var getUserListUsecase: GetUserListUsecase): ViewModel() {


    fun getUserList(): Flow<List<User>> {
        return flow {
            emit(getUserListUsecase.getUserList())
        }.flowOn(Dispatchers.IO)
    }


}