package com.example.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login : String,
    val password : String,
    val type: String
)


@Serializable
data class RegisterResponseRemote(
    val token : String
)