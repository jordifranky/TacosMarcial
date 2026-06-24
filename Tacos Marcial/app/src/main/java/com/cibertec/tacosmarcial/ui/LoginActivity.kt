package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.core.SessionManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.cibertec.tacosmarcial.db.AppDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)

        if (sessionManager.isLoggedIn()) {
            navigateToHome()
            return
        }

        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGoogleLogin = findViewById<Button>(R.id.btnGoogleLogin)
        val tvRegistrarse = findViewById<TextView>(R.id.tvRegistrarse)

        btnGoogleLogin.setOnClickListener {

            Toast.makeText(this, "Conectando con Google...", Toast.LENGTH_SHORT).show()
            

            sessionManager.createSession(
                userId = 999, 
                name = "Usuario Google", 
                email = "google.user@gmail.com"
            )
            
            Toast.makeText(this, "Bienvenido, Usuario Google", Toast.LENGTH_SHORT).show()
            navigateToHome()
        }

        tvRegistrarse.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegistroActivity::class.java
                )
            )
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Por favor, ingresa tus credenciales",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(this@LoginActivity)
                val usuario = db.usuarioDao().login(email, password)

                if (usuario != null) {
                    // AQUÍ ESTÁ EL CAMBIO: Pasamos usuario.id
                    sessionManager.createSession(
                        usuario.id,
                        usuario.nombre,
                        usuario.email
                    )
                    navigateToHome()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Correo o contraseña incorrectos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}