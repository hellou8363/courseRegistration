package org.zerock.courseregistration.domain.courseapplication.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.zerock.courseregistration.domain.courseapplication.model.CourseApplication

interface CourseApplicationRepository : JpaRepository<CourseApplication, Long> {

    fun existsByCourseIdAndUserId(courseId: Long, userId: Long): Boolean

    fun findByCourseIdAndId(courseId: Long, id: Long): CourseApplication?
}