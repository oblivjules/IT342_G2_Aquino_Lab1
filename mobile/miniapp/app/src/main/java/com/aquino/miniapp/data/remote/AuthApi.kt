package com.aquino.miniapp.data.remote

import com.aquino.miniapp.data.model.ApiResponse
import com.aquino.miniapp.data.model.AuthResponse
import com.aquino.miniapp.data.model.LoginRequest
import com.aquino.miniapp.data.model.RegisterRequest
import com.aquino.miniapp.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<Unit>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(): ApiResponse<Unit>

    @GET("user/me")
    suspend fun getCurrentUser(): ApiResponse<UserResponse>
}
