package org.zerock.courseregistration.domain.course.model

import jakarta.persistence.*
import org.zerock.courseregistration.domain.course.dto.CourseResponse
import org.zerock.courseregistration.domain.courseapplication.model.CourseApplication
import org.zerock.courseregistration.domain.lecture.model.Lecture

@Entity
@Table(name = "course")
class Course(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: CourseStatus,

    @Column(name = "max_applicants")
    val maxApplicants: Int = 30,

    @Column(name = "num_applicants")
    var numApplicants: Int = 0,

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var lectures: MutableList<Lecture> = mutableListOf(),

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var courseApplications: MutableList<CourseApplication> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isFull(): Boolean {
        return numApplicants >= maxApplicants
    }

    fun isClosed(): Boolean {
        return status == CourseStatus.CLOSED
    }

    fun close() {
        status = CourseStatus.CLOSED
    }

    fun addApplicant() {
        numApplicants += 1
    }

    fun addLecture(lecture: Lecture) {
        lectures.add(lecture)
    }

    fun removeLecture(lecture: Lecture) {
        lectures.remove(lecture)
    }

    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }
}

fun Course.toResponse(): CourseResponse {
    return CourseResponse(id!!, title, description, status.name, maxApplicants, numApplicants)
}