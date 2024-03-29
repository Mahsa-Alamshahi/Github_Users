package com.github.github_users.framework.viewmodel

import androidx.lifecycle.ViewModel
import com.github.github_users.core.data.User
import com.github.github_users.core.usecase.SearchUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(var searchUserUsecase: SearchUserUsecase): ViewModel() {


    fun searchUser(page: Int, userName: String): Flow<List<User>?> {
        return flow {
            emit(searchUserUsecase.searchUser(page, userName))
        }.flowOn(Dispatchers.IO)
    }
}