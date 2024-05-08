package org.zerock.courseregistration.domain.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.zerock.courseregistration.domain.user.dto.SignUpRequest
import org.zerock.courseregistration.domain.user.dto.UpdateUserProfileRequest
import org.zerock.courseregistration.domain.user.dto.UserResponse
import org.zerock.courseregistration.domain.user.service.UserService

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequest))
    }

    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(
        @PathVariable userId: Long,
        @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.updateUserProfile(userId, updateUserProfileRequest))
    }
}