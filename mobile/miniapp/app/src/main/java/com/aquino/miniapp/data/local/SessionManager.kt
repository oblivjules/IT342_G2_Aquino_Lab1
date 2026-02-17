package com.aquino.miniapp.data.local

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "AppPrefs"
    private const val KEY_TOKEN = "jwt_token"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_EMAIL = "email"
    private const val KEY_FIRST_NAME = "first_name"
    private const val KEY_LAST_NAME = "last_name"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        getPrefs(context).edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        return getPrefs(context).getString(KEY_TOKEN, null)
    }

    fun saveUserData(context: Context, userId: Long, email: String, firstName: String?, lastName: String?) {
        getPrefs(context).edit()
            .putLong(KEY_USER_ID, userId)
            .putString(KEY_EMAIL, email)
            .putString(KEY_FIRST_NAME, firstName)
            .putString(KEY_LAST_NAME, lastName)
            .apply()
    }

    fun getUserId(context: Context): Long {
        return getPrefs(context).getLong(KEY_USER_ID, 0)
    }

    fun getEmail(context: Context): String? {
        return getPrefs(context).getString(KEY_EMAIL, null)
    }

    fun getFirstName(context: Context): String? {
        return getPrefs(context).getString(KEY_FIRST_NAME, null)
    }

    fun getLastName(context: Context): String? {
        return getPrefs(context).getString(KEY_LAST_NAME, null)
    }

    fun clearSession(context: Context) {
        getPrefs(context).edit().clear().apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getToken(context) != null
    }
}
