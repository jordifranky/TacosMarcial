package com.cibertec.tacosmarcial.core

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "TacosMarcialPrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER_NAME = "userName"
        private const val KEY_USER_EMAIL = "userEmail"
        private const val KEY_USER_ID = "userId"
    }


    fun createSession(userId: Int, name: String, email: String) {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putInt(KEY_USER_ID, userId)
            putString(KEY_USER_NAME, name)
            putString(KEY_USER_EMAIL, email)
            apply()
        }
    }

    // Verifica si el cliente ya está autenticado
    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    // Recupera el ID del usuario (Ahora esta función existirá y no dará error)
    fun getUserId(): Int = prefs.getInt(KEY_USER_ID, -1)

    // Recupera el nombre para mostrarlo en la interfaz
    fun getUserName(): String? = prefs.getString(KEY_USER_NAME, null)

    // Destruye la sesión (Cerrar sesión)
    fun logout() {
        prefs.edit().clear().apply()
    }
}