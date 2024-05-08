package org.zerock.courseregistration.domain.courseapplication.dto

import org.zerock.courseregistration.domain.course.dto.CourseResponse
import org.zerock.courseregistration.domain.user.dto.UserResponse

data class CourseApplicationResponse(
    val id: Long,
    val course: CourseResponse,
    val user: UserResponse,
    val status: String,
)
