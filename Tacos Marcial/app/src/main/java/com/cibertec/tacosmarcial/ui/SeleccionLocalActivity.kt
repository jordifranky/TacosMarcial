package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.adapters.LocalAdapter
import com.cibertec.tacosmarcial.models.DatosApp
import com.cibertec.tacosmarcial.models.Local

class SeleccionLocalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_local)

        val rvLocales = findViewById<RecyclerView>(R.id.rvLocales)
        rvLocales.layoutManager = LinearLayoutManager(this)

        val listaLocales = listOf(
            Local(1, "Los Jardines", "Av. Los Jardines Oeste 15419"),
            Local(2, "Mall Aventura SJL", "Av. Lurigancho 997"),
            Local(3, "Canto Grande", "Av. Canto Grande"),
            Local(4, "Canto Rey", "Av. Wiesse / Canto Rey"),
            Local(5, "Bayóvar", "Av. Bayóvar Principal"),
            Local(6, "Restobar", "San Juan de Lurigancho Centro")
        )

        rvLocales.adapter = LocalAdapter(listaLocales) { localSeleccionado ->
            // Guardamos tanto el nombre como la dirección
            DatosApp.tipoEntrega = "RECOJO EN TIENDA"
            DatosApp.lugarSeleccionado = localSeleccionado.nombre
            DatosApp.direccionSeleccionada = localSeleccionado.direccion // Asegúrate de tener esta variable en DatosApp

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}