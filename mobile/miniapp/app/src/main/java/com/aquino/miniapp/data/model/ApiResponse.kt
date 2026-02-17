package com.aquino.miniapp.data.model

data class ApiResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?,
    val errors: Map<String, String>?
)
