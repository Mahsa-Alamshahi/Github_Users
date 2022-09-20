package com.github.github_users.core.usecase

import com.github.github_users.core.data.User
import com.github.github_users.core.repository.UserRepository
import javax.inject.Inject

class SearchUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun searchUser(page: Int, userName: String): List<User>? = userRepository.searchUser(page, userName)
}