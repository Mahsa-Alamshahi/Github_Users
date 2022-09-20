package com.github.github_users.core.usecase

import com.github.github_users.core.data.User
import com.github.github_users.core.repository.UserRepository
import javax.inject.Inject


class GetUserListUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun getUserList(page: Int): List<User>? = userRepository.getAllUsers(page)
}