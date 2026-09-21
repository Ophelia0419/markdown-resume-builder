package com.example.aws_project.service

import com.example.aws_project.dto.ResumeResponse
import com.example.aws_project.dto.ResumeSaveRequest
import org.springframework.stereotype.Service

@Service
class ResumeService {
    // TODO: Repository 주입 및 실제 DB 저장 로직 구현

    fun createResume(request: ResumeSaveRequest): ResumeResponse {
        return ResumeResponse(1L, request.title, request.content)
    }

    fun getResume(id: Long): ResumeResponse {
        return ResumeResponse(id, "Sample Title", "Sample Content")
    }

    fun updateResume(id: Long, request: ResumeSaveRequest): ResumeResponse {
        return ResumeResponse(id, request.title, request.content)
    }
}