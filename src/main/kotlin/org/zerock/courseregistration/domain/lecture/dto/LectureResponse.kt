package org.zerock.courseregistration.domain.lecture.dto

data class LectureResponse(
    val id: Long,
    val title: String,
    val videoUrl: String,
)
