package com.aquino.miniapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.aquino.miniapp.data.model.UserResponse

@Composable
fun ProfileScreen(
    user: UserResponse,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("Back to Dashboard")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Profile Information", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        ProfileItem(label = "Username", value = user.username)
        ProfileItem(label = "Email", value = user.email)
        ProfileItem(label = "First Name", value = user.firstName ?: "N/A")
        ProfileItem(label = "Last Name", value = user.lastName ?: "N/A")
    }
}

@Composable
private fun ProfileItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(
            user = UserResponse(
                id = 1L,
                username = "johndoe",
                email = "john@example.com",
                firstName = "John",
                lastName = "Doe",
                createdAt = "2026-02-17"
            ),
            onBack = {}
        )
    }
}
