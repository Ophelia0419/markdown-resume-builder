package com.example.resumebuilder.controller

import com.example.resumebuilder.dto.ResumeResponse
import com.example.resumebuilder.dto.ResumeSaveRequest
import com.example.resumebuilder.service.ResumeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/resumes")
class ResumeController(
    private val resumeService: ResumeService
) {
    /**
     * 새로운 이력서 생성 (POST /api/v1/resumes)
     */
    @PostMapping
    fun createResume(
        @RequestBody request: ResumeSaveRequest
    ): ResponseEntity<ResumeResponse> {
        val responseDto = resumeService.createResume(request)
        // 자원이 성공적으로 생성되었음을 의미하는 201(Created) 상태 코드를 반환하여 RESTful 표준 준수
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto)
    }

    /**
     * 특정 이력서 단건 조회 (GET /api/v1/resumes/{resumeId})
     */
    @GetMapping("/{resumeId}")
    fun getResume(
        @PathVariable resumeId: Long
    ): ResponseEntity<ResumeResponse> {
        val responseDto = resumeService.getResume(resumeId)
        // 성공적인 조회를 의미하는 200(OK) 상태 코드 반환
        return ResponseEntity.ok(responseDto)
    }

    /**
     * 기존 이력서 수정 (PUT /api/v1/resumes/{resumeId})
     */
    @PutMapping("/{resumeId}")
    fun updateResume(
        @PathVariable resumeId: Long,
        @RequestBody request: ResumeSaveRequest
    ): ResponseEntity<ResumeResponse> {
        val responseDto = resumeService.updateResume(resumeId, request)
        return ResponseEntity.ok(responseDto)
    }
}