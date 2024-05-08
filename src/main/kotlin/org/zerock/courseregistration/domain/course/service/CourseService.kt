package org.zerock.courseregistration.domain.course.service

import org.zerock.courseregistration.domain.course.dto.CourseResponse
import org.zerock.courseregistration.domain.course.dto.CreateCourseRequest
import org.zerock.courseregistration.domain.course.dto.UpdateCourseRequest

interface CourseService {

    fun getAllCourseList(): List<CourseResponse>

    fun getCourseById(courseId: Long): CourseResponse

    fun createCourse(request: CreateCourseRequest): CourseResponse

    fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun deleteCourse(courseId: Long)
}