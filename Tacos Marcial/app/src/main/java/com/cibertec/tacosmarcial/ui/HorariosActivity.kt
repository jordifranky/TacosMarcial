package com.cibertec.tacosmarcial.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.adapters.HorariosAdapter
import com.cibertec.tacosmarcial.models.DatosApp

class HorariosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horarios)

        val rvHorarios = findViewById<RecyclerView>(R.id.rvHorarios)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        rvHorarios.layoutManager = LinearLayoutManager(this)
        rvHorarios.adapter = HorariosAdapter(DatosApp.listaSucursales)

        btnVolver.setOnClickListener {
            finish()
        }
    }
}
