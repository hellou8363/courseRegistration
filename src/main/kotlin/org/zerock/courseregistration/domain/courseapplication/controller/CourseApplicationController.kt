package org.zerock.courseregistration.domain.courseapplication.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.zerock.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import org.zerock.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import org.zerock.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest

@RestController
@RequestMapping("/courses/{courseId}/applications")
class CourseApplicationController {

    @PostMapping
    fun applyCourse(
        @PathVariable courseId: Long,
        applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse> {
        TODO()
    }

    @GetMapping()
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>> {
        TODO()
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long
    ): ResponseEntity<CourseApplicationResponse> {
        TODO()
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): ResponseEntity<CourseApplicationResponse> {
        TODO()
    }
}