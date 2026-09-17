package com.example.resumebuilder.dto

import com.example.resumebuilder.entity.Resume
import java.time.LocalDateTime

/**
 * [클라이언트 -> 서버] 새로운 이력서를 생성하거나 수정할 때 받는 데이터
 */
data class ResumeSaveRequest(
    val memberId: Long,
    val title: String,
    val markdownContent: String
)

/**
 * [서버 -> 클라이언트] 조회 요청 시 클라이언트에게 반환하는 데이터
 */
data class ResumeResponse(
    val id: Long,
    val title: String,
    val markdownContent: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    // 팩토리 메서드: Entity를 안전하게 DTO로 변환하는 역할을 캡슐화
    companion object {
        fun from(entity: Resume): ResumeResponse {
            return ResumeResponse(
                id = entity.id!!,
                title = entity.title,
                markdownContent = entity.markdownContent,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }
}