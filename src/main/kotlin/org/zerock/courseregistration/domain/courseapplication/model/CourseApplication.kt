package org.zerock.courseregistration.domain.courseapplication.model

import jakarta.persistence.*
import org.zerock.courseregistration.domain.course.model.Course
import org.zerock.courseregistration.domain.course.model.toResponse
import org.zerock.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import org.zerock.courseregistration.domain.user.model.User
import org.zerock.courseregistration.domain.user.model.toResponse

@Entity
@Table(name = "course_application")
class CourseApplication(

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: CourseApplicationStatus = CourseApplicationStatus.PENDING,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isProceeded(): Boolean {
        return status != CourseApplicationStatus.PENDING
    }

    fun accept() {
        status = CourseApplicationStatus.ACCEPTED
    }

    fun reject() {
        status = CourseApplicationStatus.REJECTED
    }
}

fun CourseApplication.toResponse(): CourseApplicationResponse {
    return CourseApplicationResponse(id!!, course.toResponse(), user.toResponse(), status.name)
}