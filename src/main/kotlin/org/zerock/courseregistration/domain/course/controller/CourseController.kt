package org.zerock.courseregistration.domain.course.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.zerock.courseregistration.domain.course.dto.CourseResponse
import org.zerock.courseregistration.domain.course.dto.CreateCourseRequest
import org.zerock.courseregistration.domain.course.dto.UpdateCourseRequest
import org.zerock.courseregistration.domain.course.service.CourseService

@RequestMapping("/courses")
@RestController
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping()
    fun getCourseList(): ResponseEntity<List<CourseResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourseList())
    }

    @GetMapping("/{courseId}")
    fun getCourse(@PathVariable courseId: Long): ResponseEntity<CourseResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId))
    }

    @PostMapping
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity<CourseResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(createCourseRequest))
    }

    @PutMapping("/{courseId}")
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<CourseResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}