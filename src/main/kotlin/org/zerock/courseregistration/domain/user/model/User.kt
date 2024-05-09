package org.zerock.courseregistration.domain.user.model

import jakarta.persistence.*
import org.zerock.courseregistration.domain.courseapplication.model.CourseApplication
import org.zerock.courseregistration.domain.user.dto.UserResponse

@Entity
@Table(name = "app_user")
class User(

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Embedded
    var profile: Profile,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val courseApplications: MutableList<CourseApplication> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(id!!, email, profile.nickname, role.name)
}