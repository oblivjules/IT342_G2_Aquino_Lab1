package com.aquino.miniapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.aquino.miniapp.data.model.UserResponse

@Composable
fun DashboardScreen(
    user: UserResponse,
    onViewProfile: () -> Unit,
    onLogout: () -> Unit
) {
    val showLogoutDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = { showLogoutDialog.value = true }) {
                Text("Logout")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome, ${user.firstName ?: user.username}!",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Manage your account and view your information")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onViewProfile, modifier = Modifier.fillMaxWidth()) {
            Text("View Profile")
        }
    }

    if (showLogoutDialog.value) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog.value = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                Button(onClick = {
                    showLogoutDialog.value = false
                    onLogout()
                }) {
                    Text("Logout")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardScreen(
            user = UserResponse(
                id = 1L,
                username = "johndoe",
                email = "john@example.com",
                firstName = "John",
                lastName = "Doe",
                createdAt = "2026-02-17"
            ),
            onViewProfile = {},
            onLogout = {}
        )
    }
}
