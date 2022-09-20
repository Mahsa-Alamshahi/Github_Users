package com.github.github_users.core.usecase

import com.github.github_users.core.data.User
import com.github.github_users.core.repository.UserRepository
import javax.inject.Inject


class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun getUser(login: String): User? = userRepository.getUser(login)
}