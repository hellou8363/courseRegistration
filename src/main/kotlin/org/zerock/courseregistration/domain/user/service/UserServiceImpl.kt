package org.zerock.courseregistration.domain.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.zerock.courseregistration.domain.exception.ModelNotFoundException
import org.zerock.courseregistration.domain.user.dto.SignUpRequest
import org.zerock.courseregistration.domain.user.dto.UpdateUserProfileRequest
import org.zerock.courseregistration.domain.user.dto.UserResponse
import org.zerock.courseregistration.domain.user.model.Profile
import org.zerock.courseregistration.domain.user.model.User
import org.zerock.courseregistration.domain.user.model.UserRole
import org.zerock.courseregistration.domain.user.model.toResponse
import org.zerock.courseregistration.domain.user.repository.UserRepository

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                // TODO : 비밀번호 암호화
                password = request.password,
                profile = Profile(
                    nickname = request.nickname
                ),
                role = when (request.role) {
                    UserRole.STUDENT.name -> UserRole.STUDENT
                    UserRole.TUTOR.name -> UserRole.TUTOR
                    else -> throw IllegalStateException("Invalid role")
                }
            )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }
}