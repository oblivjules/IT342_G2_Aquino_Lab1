package com.aquino.miniapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.aquino.miniapp.ui.state.AuthUiState
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    uiState: AuthUiState,
    onRegister: suspend (String, String, String, String, String) -> Boolean,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    onClearMessages: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (localError != null) {
            Text(localError ?: "", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        } else if (uiState.errorMessage != null) {
            Text(uiState.errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        } else if (uiState.successMessage != null) {
            Text(uiState.successMessage, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                localError = null
                onClearMessages()
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                localError = null
                onClearMessages()
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName = it
                localError = null
                onClearMessages()
            },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
                localError = null
                onClearMessages()
            },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                localError = null
                onClearMessages()
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (username.isBlank()) {
                    localError = "Username is required"
                    return@Button
                }
                if (username.length < 3) {
                    localError = "Username must be at least 3 characters long"
                    return@Button
                }
                if (!Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$").matches(email)) {
                    localError = "Please enter a valid email address"
                    return@Button
                }
                if (password.length < 6) {
                    localError = "Password must be at least 6 characters long"
                    return@Button
                }
                if (firstName.isNotBlank() && firstName.length < 2) {
                    localError = "First name must be at least 2 characters long"
                    return@Button
                }
                if (lastName.isNotBlank() && lastName.length < 2) {
                    localError = "Last name must be at least 2 characters long"
                    return@Button
                }

                scope.launch {
                    val success = onRegister(
                        username.trim(),
                        email.trim(),
                        password,
                        firstName.trim(),
                        lastName.trim()
                    )
                    if (success) {
                        onRegisterSuccess()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Register")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = onLoginClick) {
            Text("Already have an account? Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(
            uiState = AuthUiState(),
            onRegister = { _, _, _, _, _ -> true },
            onRegisterSuccess = {},
            onLoginClick = {},
            onClearMessages = {}
        )
    }
}
