package com.aquino.miniapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aquino.miniapp.R
import com.aquino.miniapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var authRepository: AuthRepository

    private lateinit var identifierInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: Button
    private lateinit var errorMessage: TextView
    private lateinit var errorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authRepository = AuthRepository(this)

        identifierInput = findViewById(R.id.identifier_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        registerLink = findViewById(R.id.register_link)
        errorMessage = findViewById(R.id.error_message)
        errorContainer = findViewById(R.id.error_container)

        identifierInput.setOnFocusChangeListener { _, _ ->
            errorContainer.visibility = TextView.GONE
        }

        passwordInput.setOnFocusChangeListener { _, _ ->
            errorContainer.visibility = TextView.GONE
        }

        loginButton.setOnClickListener {
            val identifier = identifierInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if (identifier.isBlank()) {
                showError("Please enter your username or email")
                return@setOnClickListener
            }

            if (password.isBlank()) {
                showError("Password is required")
                return@setOnClickListener
            }

            if (password.length < 6) {
                showError("Password must be at least 6 characters")
                return@setOnClickListener
            }

            loginButton.isEnabled = false
            loginButton.text = "Logging in..."
            errorContainer.visibility = TextView.GONE

            lifecycleScope.launch {
                try {
                    val userResponse = authRepository.login(identifier, password)

                    if (userResponse != null) {
                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        showError("Invalid username/email or password")
                        resetLoginButton()
                    }
                } catch (e: Exception) {
                    val errorMsg = e.message ?: "Login failed"
                    val displayMessage = when {
                        errorMsg.contains("Network", ignoreCase = true) -> 
                            "Network error: Check your internet connection"
                        errorMsg.contains("401", ignoreCase = true) || 
                        errorMsg.contains("Invalid", ignoreCase = true) ||
                        errorMsg.contains("Unauthorized", ignoreCase = true) ->
                            "Invalid username/email or password"
                        errorMsg.contains("Connection", ignoreCase = true) ->
                            "Cannot connect to server"
                        else -> errorMsg
                    }
                    showError(displayMessage)
                    resetLoginButton()
                }
            }
        }

        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showError(message: String) {
        errorMessage.text = message
        errorContainer.visibility = TextView.VISIBLE
    }

    private fun resetLoginButton() {
        loginButton.isEnabled = true
        loginButton.text = "Login"
    }

    override fun onBackPressed() {
        // Exit app when back button is pressed on LoginActivity
        finishAffinity()
    }
}
