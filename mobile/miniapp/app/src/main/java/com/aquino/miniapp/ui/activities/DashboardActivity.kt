package com.aquino.miniapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aquino.miniapp.R
import com.aquino.miniapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var authRepository: AuthRepository

    private lateinit var welcomeTitle: TextView
    private lateinit var profileButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        authRepository = AuthRepository(this)

        welcomeTitle = findViewById(R.id.welcome_title)
        profileButton = findViewById(R.id.profile_button)
        logoutButton = findViewById(R.id.logout_button)

        loadUser()

        profileButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        logoutButton.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun loadUser() {

        lifecycleScope.launch {

            try {

                val user = authRepository.getCurrentUser()

                if (user != null) {
                    welcomeTitle.text =
                        "Welcome, ${user.firstName ?: user.username}!"
                } else {
                    redirectToLogin()
                }

            } catch (e: Exception) {
                // If session expired or network error, redirect to login
                redirectToLogin()

            }
        }
    }

    private fun showLogoutDialog() {

        AlertDialog.Builder(this)
            .setTitle("Confirm Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->

                logoutButton.isEnabled = false
                logoutButton.text = "Logging out..."

                lifecycleScope.launch {

                    try {

                        authRepository.logout()

                    } catch (_: Exception) {
                    }

                    redirectToLogin()

                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun redirectToLogin() {

        val intent = Intent(this, LoginActivity::class.java)

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        finish()

    }

    override fun onBackPressed() {
        // When user presses back on Dashboard, exit the app instead of going back
        finishAffinity()
        super.onBackPressed()
    }
}
