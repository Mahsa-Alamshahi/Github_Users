package com.github.github_users.core.usecase

import com.github.github_users.core.repository.UserRepository
import javax.inject.Inject


class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(url: String) = userRepository.getUser(url)
}