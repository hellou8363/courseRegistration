package org.zerock.courseregistration.domain.user.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.zerock.courseregistration.domain.user.dto.SignUpRequest
import org.zerock.courseregistration.domain.user.dto.UpdateUserProfileRequest
import org.zerock.courseregistration.domain.user.dto.UserResponse

@RestController
class UserController {

    @PostMapping("/signup")
    fun signup(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        TODO()
    }

    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(
        @PathVariable userId: Long,
        @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UserResponse> {
        TODO()
    }
}