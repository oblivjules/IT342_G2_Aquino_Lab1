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
import com.aquino.miniapp.data.local.SessionManager
import com.aquino.miniapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var authRepository: AuthRepository

    private lateinit var usernameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText

    private lateinit var registerButton: Button
    private lateinit var loginLink: Button
    private lateinit var errorMessage: TextView
    private lateinit var errorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        authRepository = AuthRepository(this)

        usernameInput = findViewById(R.id.username_input)
        emailInput = findViewById(R.id.email_input)
        firstNameInput = findViewById(R.id.first_name_input)
        lastNameInput = findViewById(R.id.last_name_input)
        passwordInput = findViewById(R.id.password_input)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)

        registerButton = findViewById(R.id.register_button)
        loginLink = findViewById(R.id.login_link)
        errorMessage = findViewById(R.id.error_message)
        errorContainer = findViewById(R.id.error_container)

        listOf(
            usernameInput,
            emailInput,
            firstNameInput,
            lastNameInput,
            passwordInput,
            confirmPasswordInput
        ).forEach { input ->
            input.setOnFocusChangeListener { _, _ ->
                errorContainer.visibility = TextView.GONE
            }
        }

        registerButton.setOnClickListener {
            performRegistration()
        }

        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun performRegistration() {
        val username = usernameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val firstName = firstNameInput.text.toString().trim()
        val lastName = lastNameInput.text.toString().trim()
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        errorContainer.visibility = TextView.GONE

        when {
            username.isBlank() -> return showError("Username is required")
            username.length < 3 -> return showError("Username must be at least 3 characters long")
            email.isBlank() -> return showError("Email is required")
            !Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$").matches(email) -> return showError("Please enter a valid email address")
            password.length < 6 -> return showError("Password must be at least 6 characters long")
            password != confirmPassword -> return showError("Passwords do not match")
            firstName.isNotBlank() && firstName.length < 2 -> return showError("First name must be at least 2 characters")
            lastName.isNotBlank() && lastName.length < 2 -> return showError("Last name must be at least 2 characters")
        }

        registerButton.isEnabled = false
        registerButton.text = "Creating account..."

        lifecycleScope.launch {
            try {
                authRepository.register(username, email, password, firstName, lastName)
                SessionManager.clearSession(this@RegisterActivity)

                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                val errorMsg = e.message ?: "An error occurred during registration"
                val displayMessage = when {
                    errorMsg.contains("Network", ignoreCase = true) ->
                        "Network error: Check your internet connection"
                    errorMsg.contains("409", ignoreCase = true) ||
                            errorMsg.contains("already exists", ignoreCase = true) ->
                        "Username or email already exists"
                    errorMsg.contains("Connection", ignoreCase = true) ->
                        "Cannot connect to server"
                    else -> errorMsg
                }
                showError(displayMessage)
                resetButton()
            }
        }
    }

    private fun showError(message: String) {
        errorMessage.text = message
        errorContainer.visibility = TextView.VISIBLE
    }

    private fun resetButton() {
        registerButton.isEnabled = true
        registerButton.text = "Register"
    }

    override fun onBackPressed() {
        // Go back to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
