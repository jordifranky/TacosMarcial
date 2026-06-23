package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.net.Uri // Necesario para abrir enlaces
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView // Necesario para tus botones de redes
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.core.SessionManager
import com.cibertec.tacosmarcial.models.DatosApp

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        val nombreUsuario = sessionManager.getUserName() ?: "Cliente"
        tvBienvenida.text = "¡Hola, $nombreUsuario!"

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            sessionManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnParaLlevar = findViewById<LinearLayout>(R.id.btnParaLlevar)
        val btnADomicilio = findViewById<LinearLayout>(R.id.btnADomicilio)

        btnParaLlevar.setOnClickListener {
            DatosApp.tipoEntrega = "Recojo en Tienda"
            actualizarUIEntrega(btnParaLlevar, btnADomicilio)
            val intent = Intent(this, SeleccionLocalActivity::class.java)
            startActivity(intent)
        }

        btnADomicilio.setOnClickListener {
            DatosApp.tipoEntrega = "A domicilio"
            actualizarUIEntrega(btnParaLlevar, btnADomicilio)
            val intent = Intent(this, SeleccionSucursalActivity::class.java)
            startActivity(intent)
        }

        val btnMenuDigital = findViewById<LinearLayout>(R.id.btnMenuDigital)
        val btnPedir = findViewById<Button>(R.id.btnPedir)
        val btnHorarios = findViewById<LinearLayout>(R.id.btnHorarios)

        btnMenuDigital.setOnClickListener {
            val intent = Intent(this, SeleccionSucursalActivity::class.java)
            startActivity(intent)
        }

        btnPedir.setOnClickListener {
            val intent = Intent(this, SeleccionSucursalActivity::class.java)
            startActivity(intent)
        }

        btnHorarios.setOnClickListener {
            val intent = Intent(this, HorariosActivity::class.java)
            startActivity(intent)
        }

        val btnHistorial = findViewById<LinearLayout>(R.id.btnHistorial)
        btnHistorial.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)
        }

        // --- LÓGICA DE REDES SOCIALES Y WHATSAPP ---

        // 1.Instagram
        findViewById<ImageView>(R.id.btnInstagram).setOnClickListener {
            abrirEnlace("https://www.instagram.com/tacosmarcialrestobar/")
        }

        // 2.Facebook
        findViewById<ImageView>(R.id.btnFacebook).setOnClickListener {
            abrirEnlace("https://www.facebook.com/tacosmarcialoficial/?locale=es_LA")
        }

        // 3.TikTok
        findViewById<ImageView>(R.id.btnTikTok).setOnClickListener {
            abrirEnlace("https://www.tiktok.com/@tacosmarcial")
        }

        // 4.WhatsApp
        findViewById<ImageView>(R.id.btnWhatsApp).setOnClickListener {
            val numero = "51948327199"
            abrirEnlace("https://wa.me/$numero")
        }
    }

    // Función auxiliar para abrir cualquier enlace o app
    private fun abrirEnlace(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No se pudo abrir la aplicación", Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarUIEntrega(btnRecojo: LinearLayout, btnDomicilio: LinearLayout) {
        if (DatosApp.tipoEntrega == "Recojo en Tienda") {
            btnRecojo.setBackgroundResource(R.drawable.bg_service_selected)
            btnDomicilio.setBackgroundResource(R.drawable.bg_service_default)
        } else {
            btnRecojo.setBackgroundResource(R.drawable.bg_service_default)
            btnDomicilio.setBackgroundResource(R.drawable.bg_service_selected)
        }
    }
}