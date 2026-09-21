package com.example.aws_project.dto

data class ResumeSaveRequest(
    val title: String,
    val content: String
)

data class ResumeResponse(
    val id: Long,
    val title: String,
    val content: String
) {
    companion object {
        fun from(resume: com.example.aws_project.entity.Resume): ResumeResponse {
            return ResumeResponse(
                id = resume.id!!,
                title = resume.title,
                content = resume.content
            )
        }
    }
}