package com.aquino.miniapp.data.remote

import com.aquino.miniapp.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

object ApiClient {
    // Use 10.0.2.2 for Android Emulator, or your computer's IP for physical device
    private const val BASE_URL = "http://192.168.1.10:8080/api"
    private val JSON_MEDIA = "application/json; charset=utf-8".toMediaType()
    private val client = OkHttpClient()
    private val gson = Gson()

    interface ApiCallback<T> {
        fun onSuccess(result: T)
        fun onError(error: String)
    }

    fun register(
        username: String,
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        callback: ApiCallback<AuthResponse>
    ) {
        val requestData = RegisterRequest(username, email, password, firstName, lastName)
        val body = gson.toJson(requestData).toRequestBody(JSON_MEDIA)
        
        val request = Request.Builder()
            .url("$BASE_URL/auth/register")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError("Network error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                
                if (response.isSuccessful) {
                    try {
                        val type = object : TypeToken<ApiResponse<AuthResponse>>() {}.type
                        val apiResponse: ApiResponse<AuthResponse> = gson.fromJson(responseBody, type)
                        
                        if (apiResponse.success && apiResponse.data != null) {
                            callback.onSuccess(apiResponse.data)
                        } else {
                            callback.onError(apiResponse.message ?: "Registration failed")
                        }
                    } catch (e: Exception) {
                        callback.onError("Failed to parse response: ${e.message}")
                    }
                } else {
                    try {
                        val type = object : TypeToken<ApiResponse<AuthResponse>>() {}.type
                        val errorResponse: ApiResponse<AuthResponse> = gson.fromJson(responseBody, type)
                        val errorMessage = errorResponse.message ?: "Registration failed: ${response.code}"
                        callback.onError(errorMessage)
                    } catch (e: Exception) {
                        callback.onError("Registration failed: ${response.code}")
                    }
                }
            }
        })
    }

    fun login(email: String, password: String, callback: ApiCallback<AuthResponse>) {
        val requestData = LoginRequest(email, password)
        val body = gson.toJson(requestData).toRequestBody(JSON_MEDIA)
        
        val request = Request.Builder()
            .url("$BASE_URL/auth/login")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError("Network error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                
                if (response.isSuccessful) {
                    try {
                        val type = object : TypeToken<ApiResponse<AuthResponse>>() {}.type
                        val apiResponse: ApiResponse<AuthResponse> = gson.fromJson(responseBody, type)
                        
                        if (apiResponse.success && apiResponse.data != null) {
                            callback.onSuccess(apiResponse.data)
                        } else {
                            callback.onError(apiResponse.message ?: "Login failed")
                        }
                    } catch (e: Exception) {
                        callback.onError("Failed to parse response: ${e.message}")
                    }
                } else {
                    try {
                        val type = object : TypeToken<ApiResponse<AuthResponse>>() {}.type
                        val errorResponse: ApiResponse<AuthResponse> = gson.fromJson(responseBody, type)
                        val errorMessage = errorResponse.message ?: when(response.code) {
                            401 -> "Invalid username/email or password"
                            else -> "Login failed: ${response.code}"
                        }
                        callback.onError(errorMessage)
                    } catch (e: Exception) {
                        callback.onError(when(response.code) {
                            401 -> "Invalid username/email or password"
                            else -> "Login failed: ${response.code}"
                        })
                    }
                }
            }
        })
    }

    fun getProfile(token: String, callback: ApiCallback<UserResponse>) {
        val request = Request.Builder()
            .url("$BASE_URL/user/me")
            .addHeader("Authorization", "Bearer $token")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError("Network error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                
                if (response.isSuccessful) {
                    try {
                        val type = object : TypeToken<ApiResponse<UserResponse>>() {}.type
                        val apiResponse: ApiResponse<UserResponse> = gson.fromJson(responseBody, type)
                        
                        if (apiResponse.success && apiResponse.data != null) {
                            callback.onSuccess(apiResponse.data)
                        } else {
                            callback.onError(apiResponse.message ?: "Failed to get profile")
                        }
                    } catch (e: Exception) {
                        callback.onError("Failed to parse response: ${e.message}")
                    }
                } else {
                    try {
                        val type = object : TypeToken<ApiResponse<UserResponse>>() {}.type
                        val errorResponse: ApiResponse<UserResponse> = gson.fromJson(responseBody, type)
                        val errorMessage = errorResponse.message ?: when(response.code) {
                            401 -> "Session expired. Please login again"
                            else -> "Failed to get profile: ${response.code}"
                        }
                        callback.onError(errorMessage)
                    } catch (e: Exception) {
                        callback.onError(when(response.code) {
                            401 -> "Session expired. Please login again"
                            else -> "Failed to get profile: ${response.code}"
                        })
                    }
                }
            }
        })
    }

    fun logout(token: String, callback: ApiCallback<String>) {
        val request = Request.Builder()
            .url("$BASE_URL/auth/logout")
            .addHeader("Authorization", "Bearer $token")
            .post("".toRequestBody(null))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError("Network error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                
                if (response.isSuccessful) {
                    try {
                        val type = object : TypeToken<ApiResponse<Void>>() {}.type
                        val apiResponse: ApiResponse<Void> = gson.fromJson(responseBody, type)
                        callback.onSuccess(apiResponse.message ?: "Logged out successfully")
                    } catch (e: Exception) {
                        callback.onSuccess("Logged out")
                    }
                } else {
                    callback.onError("Logout failed: ${response.code}")
                }
            }
        })
    }
}
