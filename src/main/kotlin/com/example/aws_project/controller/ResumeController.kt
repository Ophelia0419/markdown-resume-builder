package com.example.aws_project.controller

import com.example.aws_project.dto.ResumeResponse
import com.example.aws_project.dto.ResumeSaveRequest
import com.example.aws_project.service.ResumeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/resumes")
class ResumeController(
    private val resumeService: ResumeService
) {
    @PostMapping
    fun createResume(@RequestBody request: ResumeSaveRequest): ResponseEntity<ResumeResponse> {
        val response = resumeService.createResume(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getResume(@PathVariable id: Long): ResponseEntity<ResumeResponse> {
        val response = resumeService.getResume(id)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updateResume(
        @PathVariable id: Long,
        @RequestBody request: ResumeSaveRequest
    ): ResponseEntity<ResumeResponse> {
        val response = resumeService.updateResume(id, request)
        return ResponseEntity.ok(response)
    }
}