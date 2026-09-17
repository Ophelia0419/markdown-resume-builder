package com.example.resumebuilder.repository

import com.example.resumebuilder.entity.Resume
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResumeRepository : JpaRepository<Resume, Long> {
    // 특정 회원이 작성한 모든 이력서 목록을 최신순으로 조회
    // SELECT * FROM resumes WHERE member_id = ? ORDER BY created_at DESC
    fun findAllByMemberIdOrderByCreatedAtDesc(memberId: Long): List<Resume>
}