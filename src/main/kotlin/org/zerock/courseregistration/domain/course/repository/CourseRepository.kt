package org.zerock.courseregistration.domain.course.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.zerock.courseregistration.domain.course.model.Course

interface CourseRepository : JpaRepository<Course, Long> {
}