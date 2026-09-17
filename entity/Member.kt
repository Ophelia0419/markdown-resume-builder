package com.example.resumebuilder.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "members")
class Member(
    // 이메일은 중복 가입을 막기 위해 Unique 제약조건 설정
    @Column(nullable = false, unique = true, length = 100)
    val email: String,

    @Column(nullable = false, length = 50)
    val name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
}