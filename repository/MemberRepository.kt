package com.example.resumebuilder.repository

import com.example.resumebuilder.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    // 이메일을 기준으로 특정 회원을 조회하는 쿼리 자동 생성 (SELECT * FROM members WHERE email = ?)
    fun findByEmail(email: String): Member?
}