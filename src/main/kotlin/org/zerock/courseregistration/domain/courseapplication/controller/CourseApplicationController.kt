package org.zerock.courseregistration.domain.courseapplication.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.zerock.courseregistration.domain.course.service.CourseService
import org.zerock.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import org.zerock.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import org.zerock.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest

@RestController
@RequestMapping("/courses/{courseId}/applications")
class CourseApplicationController(
    private val courseService: CourseService
) {

    @PostMapping
    fun applyCourse(
        @PathVariable courseId: Long,
        @RequestBody applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.applyCourse(courseId, applyCourseRequest))
    }

    @GetMapping()
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseApplicationList(courseId))
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseApplication(courseId, applicationId))
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(courseService.updateCourseApplicationStatus(courseId, applicationId, updateApplicationStatusRequest))
    }
}