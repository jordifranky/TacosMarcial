package com.cibertec.tacosmarcial.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.db.AppDatabase
import com.cibertec.tacosmarcial.models.Usuario
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnVolverLogin = findViewById<TextView>(R.id.btnVolverLogin)

        btnVolverLogin.setOnClickListener {
            finish()
        }

        btnRegistrar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (
                nombre.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            lifecycleScope.launch {

                val db = AppDatabase.getDatabase(this@RegistroActivity)

                val usuarioExistente =
                    db.usuarioDao().buscarPorEmail(email)

                if (usuarioExistente != null) {

                    Toast.makeText(
                        this@RegistroActivity,
                        "Ese correo ya existe",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    db.usuarioDao().insertar(
                        Usuario(
                            nombre = nombre,
                            email = email,
                            password = password
                        )
                    )

                    Toast.makeText(
                        this@RegistroActivity,
                        "Usuario registrado",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }
        }
    }
}