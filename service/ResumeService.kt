package com.example.resumebuilder.service

import com.example.resumebuilder.dto.ResumeResponse
import com.example.resumebuilder.dto.ResumeSaveRequest
import com.example.resumebuilder.entity.Resume
import com.example.resumebuilder.repository.MemberRepository
import com.example.resumebuilder.repository.ResumeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResumeService(
    private val resumeRepository: ResumeRepository,
    private val memberRepository: MemberRepository
) {
    /**
     * 새로운 마크다운 이력서를 생성하고 저장합니다.
     * @Transactional: 도중에 에러가 발생하면 DB 상태를 이전으로 완벽히 롤백(Rollback)하여 ACID 무결성을 보장
     */
    @Transactional
    fun createResume(request: ResumeSaveRequest): ResumeResponse {
        // 1. 회원 검증: 요청된 회원 ID가 DB에 실제로 존재하는지 확인 (Fail-Fast 원칙 적용)
        val member = memberRepository.findByIdOrNull(request.memberId)
            ?: throw IllegalArgumentException("존재하지 않는 회원입니다. Member ID: ${request.memberId}")

        // 2. Entity 생성 및 저장
        val resume = Resume(
            member = member,
            title = request.title,
            markdownContent = request.markdownContent
        )
        val savedResume = resumeRepository.save(resume)

        // 3. DTO로 변환하여 반환 (Entity 외부 노출 차단)
        return ResumeResponse.from(savedResume)
    }

    /**
     * 특정 이력서 단건을 조회합니다.
     * readOnly = true: 데이터 수정이 일어나지 않는 조회 전용 메서드임을 명시하여,
     * 내부적으로 DB 성능을 최적화하고 불필요한 스냅샷 저장을 방지
     */
    @Transactional(readOnly = true)
    fun getResume(resumeId: Long): ResumeResponse {
        val resume = resumeRepository.findByIdOrNull(resumeId)
            ?: throw IllegalArgumentException("이력서를 찾을 수 없습니다. Resume ID: $resumeId")

        return ResumeResponse.from(resume)
    }

    /**
     * 기존 이력서의 제목과 내용을 수정
     */
    @Transactional
    fun updateResume(resumeId: Long, request: ResumeSaveRequest): ResumeResponse {
        val resume = resumeRepository.findByIdOrNull(resumeId)
            ?: throw IllegalArgumentException("수정할 이력서를 찾을 수 없습니다. Resume ID: $resumeId")

        // 엔티티 내부의 캡슐화된 메서드를 호출하여 상태를 변경 (객체지향적 설계)
        resume.updateContent(request.title, request.markdownContent)

        // JPA의 '더티 체킹(Dirty Checking)' 기능 덕분에 별도의 save() 호출 없이도
        // 트랜잭션이 종료될 때 변경 사항이 DB에 자동으로 UPDATE 쿼리로 날아가.
        return ResumeResponse.from(resume)
    }
}