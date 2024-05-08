package org.zerock.courseregistration.domain.course.dto

data class UpdateCourseRequest(
    val title: String,
    val description: String?,
)