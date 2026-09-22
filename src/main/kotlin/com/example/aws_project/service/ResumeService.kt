package com.example.aws_project.service

import com.example.aws_project.dto.ResumeResponse
import com.example.aws_project.dto.ResumeSaveRequest
import com.example.aws_project.entity.Resume
import com.example.aws_project.repository.ResumeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ResumeService(
    private val resumeRepository: ResumeRepository
) {

    @Transactional
    fun createResume(request: ResumeSaveRequest): ResumeResponse {
        val resume = Resume(
            title = request.title,
            content = request.content
        )

        val savedResume = resumeRepository.save(resume)

        return ResumeResponse.from(savedResume)
    }

    fun getResume(id: Long): ResumeResponse {
        val resume = resumeRepository.findById(id)
            .orElseThrow {
                IllegalArgumentException(
                    "해당 이력서를 찾을 수 없습니다. id=$id"
                )
            }

        return ResumeResponse.from(resume)
    }

    @Transactional
    fun updateResume(
        id: Long,
        request: ResumeSaveRequest
    ): ResumeResponse {
        val resume = resumeRepository.findById(id)
            .orElseThrow {
                IllegalArgumentException(
                    "해당 이력서를 찾을 수 없습니다. id=$id"
                )
            }

        resume.update(
            title = request.title,
            content = request.content
        )

        return ResumeResponse.from(resume)
    }
}