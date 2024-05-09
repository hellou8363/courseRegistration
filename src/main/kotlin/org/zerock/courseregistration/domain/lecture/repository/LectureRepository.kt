package org.zerock.courseregistration.domain.lecture.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.zerock.courseregistration.domain.lecture.model.Lecture

interface LectureRepository : JpaRepository<Lecture, Long> {

    fun findByCourseIdAndId(courseId: Long, lectureId: Long): Lecture?
}