package org.zerock.courseregistration.domain.course.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.zerock.courseregistration.domain.course.dto.CourseResponse
import org.zerock.courseregistration.domain.course.dto.CreateCourseRequest
import org.zerock.courseregistration.domain.course.dto.UpdateCourseRequest
import org.zerock.courseregistration.domain.course.model.Course
import org.zerock.courseregistration.domain.course.model.CourseStatus
import org.zerock.courseregistration.domain.course.model.toResponse
import org.zerock.courseregistration.domain.course.repository.CourseRepository
import org.zerock.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import org.zerock.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import org.zerock.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import org.zerock.courseregistration.domain.courseapplication.model.CourseApplication
import org.zerock.courseregistration.domain.courseapplication.model.CourseApplicationStatus
import org.zerock.courseregistration.domain.courseapplication.model.toResponse
import org.zerock.courseregistration.domain.courseapplication.repository.CourseApplicationRepository
import org.zerock.courseregistration.domain.exception.ModelNotFoundException
import org.zerock.courseregistration.domain.lecture.dto.AddLectureRequest
import org.zerock.courseregistration.domain.lecture.dto.LectureResponse
import org.zerock.courseregistration.domain.lecture.dto.UpdateLectureRequest
import org.zerock.courseregistration.domain.lecture.model.Lecture
import org.zerock.courseregistration.domain.lecture.model.toResponse
import org.zerock.courseregistration.domain.lecture.repository.LectureRepository
import org.zerock.courseregistration.domain.user.repository.UserRepository

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val lectureRepository: LectureRepository,
    private val userRepository: UserRepository,
    private val courseApplicationRepository: CourseApplicationRepository
) : CourseService {
    override fun getAllCourseList(): List<CourseResponse> {
        return courseRepository.findAll().map { it.toResponse() }
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.toResponse()
    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        return courseRepository.save(
            Course(
                title = request.title,
                description = request.description,
                status = CourseStatus.OPEN,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val (title, description) = request

        course.title = title
        course.description = description

        return courseRepository.save(course).toResponse()
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        courseRepository.delete(course)
    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.lectures.map { it.toResponse() }
    }

    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId) ?: throw ModelNotFoundException(
            "Lecture",
            lectureId
        )
        return lecture.toResponse()
    }

    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        val lecture = Lecture(
            title = request.title,
            videoUrl = request.videoUrl,
            course = course
        )

        // Course에 Lecture 추가
        course.addLecture(lecture)
        lectureRepository.save(lecture)

        // Lecture에 영속성을 전파
        courseRepository.save(course)

        return lecture.toResponse()
    }

    @Transactional
    override fun updateLecture(courseId: Long, lectureId: Long, request: UpdateLectureRequest): LectureResponse {
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId) ?: throw ModelNotFoundException(
            "Lecture",
            lectureId
        )

        val (title, videoUrl) = request

        lecture.title = title
        lecture.videoUrl = videoUrl

        return lectureRepository.save(lecture).toResponse()
    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val lecture = lectureRepository.findByIdOrNull(lectureId) ?: throw ModelNotFoundException("Lecture", lectureId)

        course.removeLecture(lecture)

        // Lecture에 영속성을 전파
        courseRepository.save(course)
    }

    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", request.userId)

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // CourseApplication 중복 체크
        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)) {
            throw IllegalStateException("Already applied. courseId: $courseId, userId: ${request.userId}")
        }

        val courseApplication = CourseApplication(
            course = course,
            user = user,
        )

        course.addCourseApplication(courseApplication)

        courseApplicationRepository.save(courseApplication)
        // CourseApplication에 영속성을 전파
        courseRepository.save(course)

        return courseApplication.toResponse()
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CouseApplication", applicationId)

        return application.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        return course.courseApplications.map { it.toResponse() }
    }

    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        // 이미 승인 혹은 거절된 신청건인지 체크
        if (application.isProceeded()) {
            throw IllegalStateException("Application is already proceeded, applicationId: $applicationId")
        }

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // 승인 / 거절에 따른 처리
        when (request.status) {
            // 승인일 때
            CourseApplicationStatus.ACCEPTED.name -> {
                // 승인 처리
                application.accept()
                // Course의 신청 인원 늘려줌
                course.addApplicant()
                // 신청 인원이 꽉 찬다면 마감 처리
                if (course.isFull()) {
                    course.close()
                }

                courseRepository.save(course)
            }

            // 거절일 때
            CourseApplicationStatus.REJECTED.name -> {
                // 거절 처리
                application.reject()
            }

            // 승인, 거절이 아닌 다른 인자가 들어올 경우 에러 처리
            else -> {
                throw IllegalStateException("Invalid status: ${request.status}")
            }
        }

        return courseApplicationRepository.save(application).toResponse()
    }
}