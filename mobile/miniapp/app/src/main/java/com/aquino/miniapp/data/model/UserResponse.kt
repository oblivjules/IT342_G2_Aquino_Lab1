package com.aquino.miniapp.data.model

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val createdAt: String
)
