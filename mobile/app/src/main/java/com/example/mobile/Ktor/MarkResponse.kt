package com.example.mobile.Ktor

import kotlinx.serialization.Serializable



@Serializable
data class MarkResponse(
    val id: Int,
    val studentId: Int,
    val subject: String,
    val mark: Int,
    val comment: String,
    val teacher: String,
    val type: String
)
