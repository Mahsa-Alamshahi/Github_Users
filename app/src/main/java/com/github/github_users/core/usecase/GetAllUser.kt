package com.github.github_users.core.usecase

import com.github.github_users.core.repository.UserRepository


class GetAllUsers(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getAllUser()
}