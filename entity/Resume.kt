package com.example.resumebuilder.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "resumes")
class Resume(
    // 회원(Member)과의 N:1 관계 매핑
    // FetchType.LAZY(지연 로딩)를 설정하여 불필요한 조인(Join) 쿼리로 인한 성능 저하(N+1 문제) 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    @Column(nullable = false, length = 200)
    var title: String,

    // 마크다운 원본은 길이가 길어질 수 있으므로 TEXT 타입으로 명시
    @Column(nullable = false, columnDefinition = "TEXT")
    var markdownContent: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set // 외부에서 직접 날짜를 조작하지 못하도록 캡슐화

    /**
     * 비즈니스 로직: 이력서 내용 수정
     */
    fun updateContent(newTitle: String, newContent: String) {
        this.title = newTitle
        this.markdownContent = newContent
        this.updatedAt = LocalDateTime.now()
    }
}