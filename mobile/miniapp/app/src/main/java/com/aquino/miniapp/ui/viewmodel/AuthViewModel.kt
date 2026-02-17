package com.aquino.miniapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aquino.miniapp.data.repository.AuthRepository
import com.aquino.miniapp.ui.state.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState(isLoading = true))
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {
            val user = repository.getCurrentUser()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isInitialized = true,
                    user = user,
                    errorMessage = null
                )
            }
        }
    }

    suspend fun login(identifier: String, password: String): Boolean {
        _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
        return try {
            val user = repository.login(identifier, password)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = user,
                    errorMessage = null
                )
            }
            true
        } catch (ex: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = ex.message ?: "Login failed"
                )
            }
            false
        }
    }

    suspend fun register(
        username: String,
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Boolean {
        _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
        return try {
            val user = repository.register(username, email, password, firstName, lastName)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = user,
                    successMessage = "Account created successfully",
                    errorMessage = null
                )
            }
            true
        } catch (ex: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = ex.message ?: "Registration failed"
                )
            }
            false
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.logout()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = null,
                    successMessage = null
                )
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
