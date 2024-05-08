package org.zerock.courseregistration.domain.user.service

import org.zerock.courseregistration.domain.user.dto.SignUpRequest
import org.zerock.courseregistration.domain.user.dto.UpdateUserProfileRequest
import org.zerock.courseregistration.domain.user.dto.UserResponse

interface UserService {

    fun signUp(request: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse
}