package org.zerock.courseregistration.domain.lecture.model

import jakarta.persistence.*
import org.zerock.courseregistration.domain.course.model.Course
import org.zerock.courseregistration.domain.lecture.dto.LectureResponse

@Entity
@Table(name = "lecture")
class Lecture(

    @Column(name = "title")
    var title: String,

    @Column(name = "video_url")
    var videoUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    val course: Course
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Lecture.toResponse(): LectureResponse {
    return LectureResponse(id!!, title, videoUrl)
}