package com.github.github_users.core.usecase

import com.github.github_users.core.repository.UserRepository


class GetUser(private val userRepository: UserRepository) {
    suspend operator fun invoke(url: String) = userRepository.getUser(url)
}