package org.zerock.courseregistration.domain.course.service

import org.springframework.stereotype.Service
import org.zerock.courseregistration.domain.course.dto.CourseResponse
import org.zerock.courseregistration.domain.course.dto.CreateCourseRequest
import org.zerock.courseregistration.domain.course.dto.UpdateCourseRequest

@Service
class CourseServiceImpl: CourseService {
    override fun getAllCourseList(): List<CourseResponse> {
        // TODO : DB에서 모든 Course 목록을 조회하여 CourseResponse 목록으로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        // TODO : DB에서 ID기반으로 Course를 가져와서 CourseResponse로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        // TODO : request를 Course로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }

    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        // TODO : DB에서 courseId에 해당하는 Course를 가져와서 request기반으로 업데이트 후 DB에 저장, 결과를 CourseResponse로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun deleteCourse(courseId: Long) {
        // TODO : DB에서 courseId에 해당하는 Course를 삭제, 연관된 CourseApplication과 Leture도 모두 삭제
        TODO("Not yet implemented")
    }
}