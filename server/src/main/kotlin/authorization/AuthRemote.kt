package com.example.authorization

import kotlinx.serialization.Serializable








@Serializable
data class LoginReceiveRemote(
    val login: String,
    val password: String
)


@Serializable
data class LoginResponseRemote(
    val token: String,
    val studentId: Int,
    val groupId: Int,
    val type: String
)