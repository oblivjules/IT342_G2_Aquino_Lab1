package com.aquino.miniapp.data.repository

import android.content.Context
import com.aquino.miniapp.data.local.SessionManager
import com.aquino.miniapp.data.model.AuthResponse
import com.aquino.miniapp.data.model.UserResponse
import com.aquino.miniapp.data.remote.ApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepository(private val context: Context) {

    suspend fun login(email: String, password: String): UserResponse {
        // First, login and get the token
        val authResponse = suspendCancellableCoroutine<AuthResponse> { continuation ->
            ApiClient.login(email, password, object : ApiClient.ApiCallback<AuthResponse> {
                override fun onSuccess(result: AuthResponse) {
                    continuation.resume(result)
                }

                override fun onError(error: String) {
                    continuation.resumeWithException(Exception(error))
                }
            })
        }

        // Save the token
        SessionManager.saveToken(context, authResponse.token)

        // Then get user profile
        val userResponse = suspendCancellableCoroutine<UserResponse> { continuation ->
            ApiClient.getProfile(authResponse.token, object : ApiClient.ApiCallback<UserResponse> {
                override fun onSuccess(result: UserResponse) {
                    continuation.resume(result)
                }

                override fun onError(error: String) {
                    continuation.resumeWithException(Exception(error))
                }
            })
        }

        // Save user data
        SessionManager.saveUserData(
            context,
            userResponse.id,
            userResponse.email,
            userResponse.firstName,
            userResponse.lastName
        )

        return userResponse
    }

    suspend fun register(
        username: String,
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): UserResponse {
        // First, register and get the token
        val authResponse = suspendCancellableCoroutine<AuthResponse> { continuation ->
            ApiClient.register(username, email, password, firstName, lastName, object : ApiClient.ApiCallback<AuthResponse> {
                override fun onSuccess(result: AuthResponse) {
                    continuation.resume(result)
                }

                override fun onError(error: String) {
                    continuation.resumeWithException(Exception(error))
                }
            })
        }

        // Save the token
        SessionManager.saveToken(context, authResponse.token)

        // Then get user profile
        val userResponse = suspendCancellableCoroutine<UserResponse> { continuation ->
            ApiClient.getProfile(authResponse.token, object : ApiClient.ApiCallback<UserResponse> {
                override fun onSuccess(result: UserResponse) {
                    continuation.resume(result)
                }

                override fun onError(error: String) {
                    continuation.resumeWithException(Exception(error))
                }
            })
        }

        // Save user data
        SessionManager.saveUserData(
            context,
            userResponse.id,
            userResponse.email,
            userResponse.firstName,
            userResponse.lastName
        )

        return userResponse
    }

    suspend fun logout() {
        val token = SessionManager.getToken(context)
        if (token != null) {
            try {
                suspendCancellableCoroutine<String> { continuation ->
                    ApiClient.logout(token, object : ApiClient.ApiCallback<String> {
                        override fun onSuccess(result: String) {
                            continuation.resume(result)
                        }

                        override fun onError(error: String) {
                            continuation.resume("Logged out locally")
                        }
                    })
                }
            } finally {
                SessionManager.clearSession(context)
            }
        }
    }

    suspend fun getCurrentUser(): UserResponse? {
        val token = SessionManager.getToken(context) ?: return null

        return try {
            suspendCancellableCoroutine<UserResponse> { continuation ->
                ApiClient.getProfile(token, object : ApiClient.ApiCallback<UserResponse> {
                    override fun onSuccess(result: UserResponse) {
                        continuation.resume(result)
                    }

                    override fun onError(error: String) {
                        continuation.resumeWithException(Exception(error))
                    }
                })
            }
        } catch (e: Exception) {
            SessionManager.clearSession(context)
            null
        }
    }

    fun hasToken(): Boolean {
        return SessionManager.isLoggedIn(context)
    }
}
