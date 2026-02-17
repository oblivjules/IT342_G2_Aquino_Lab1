package com.aquino.miniapp.ui.state

import com.aquino.miniapp.data.model.UserResponse

data class AuthUiState(
    val isLoading: Boolean = false,
    val isInitialized: Boolean = false,
    val user: UserResponse? = null,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
