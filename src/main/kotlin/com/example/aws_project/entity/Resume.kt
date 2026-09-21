package com.example.aws_project.entity

import jakarta.persistence.*

@Entity
@Table(name = "resumes")
class Resume(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    var title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String
) {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}