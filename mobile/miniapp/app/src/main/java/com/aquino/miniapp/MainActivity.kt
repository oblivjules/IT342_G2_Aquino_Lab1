package com.aquino.miniapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aquino.miniapp.data.local.SessionManager
import com.aquino.miniapp.ui.activities.DashboardActivity
import com.aquino.miniapp.ui.activities.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val token = SessionManager.getToken(this)
        
        val intent = if (token != null) {
            Intent(this, DashboardActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        
        startActivity(intent)
        finish()
    }
}