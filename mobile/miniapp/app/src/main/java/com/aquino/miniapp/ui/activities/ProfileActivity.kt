package com.aquino.miniapp.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aquino.miniapp.R
import com.aquino.miniapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var authRepository: AuthRepository

    private lateinit var backButton: Button
    private lateinit var usernameValue: TextView
    private lateinit var emailValue: TextView
    private lateinit var firstNameValue: TextView
    private lateinit var lastNameValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        authRepository = AuthRepository(this)

        backButton = findViewById(R.id.back_button)
        usernameValue = findViewById(R.id.username_value)
        emailValue = findViewById(R.id.email_value)
        firstNameValue = findViewById(R.id.first_name_value)
        lastNameValue = findViewById(R.id.last_name_value)

        loadProfile()

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadProfile() {
        lifecycleScope.launch {
            try {
                val user = authRepository.getCurrentUser()

                if (user != null) {
                    usernameValue.text = user.username
                    emailValue.text = user.email
                    firstNameValue.text = user.firstName ?: "N/A"
                    lastNameValue.text = user.lastName ?: "N/A"
                } else {
                    usernameValue.text = "Not available"
                    emailValue.text = "Not available"
                    firstNameValue.text = "Not available"
                    lastNameValue.text = "Not available"
                }
            } catch (e: Exception) {
                usernameValue.text = "Error loading profile"
                emailValue.text = "-"
                firstNameValue.text = "-"
                lastNameValue.text = "-"
            }
        }
    }
}
