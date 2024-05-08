package org.zerock.courseregistration.domain.lecture.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.zerock.courseregistration.domain.lecture.dto.AddLectureRequest
import org.zerock.courseregistration.domain.lecture.dto.LectureResponse
import org.zerock.courseregistration.domain.lecture.dto.UpdateLectureRequest

@RestController
@RequestMapping("/courses/{courseId}/lectures")
class LectureController(
//    private val lectureService: Service
) {

    @GetMapping
    fun getLectureList(@PathVariable courseId: Long): ResponseEntity<List<LectureResponse>> {
        TODO()
    }

    @GetMapping("/{lectureId}")
    fun getLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<LectureResponse> {
        TODO()
    }

    @PostMapping()
    fun addLecture(
        @PathVariable courseId: Long,
        @RequestBody addLectureRequest: AddLectureRequest
    ): ResponseEntity<LectureResponse> {
        TODO()
    }

    @PutMapping("/{lectureId}")
    fun updateLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureId: Long,
        @RequestBody updateLectureRequest: UpdateLectureRequest
    ): ResponseEntity<LectureResponse> {
        TODO()
    }

    @DeleteMapping("/{lectureId}")
    fun removeLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<Unit> {
        TODO()
    }
}