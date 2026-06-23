package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R

class PedidoExitosoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pedido_exitoso)

        val btnVolverInicio =
            findViewById<Button>(R.id.btnVolverInicio)

        btnVolverInicio.setOnClickListener {

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }
}