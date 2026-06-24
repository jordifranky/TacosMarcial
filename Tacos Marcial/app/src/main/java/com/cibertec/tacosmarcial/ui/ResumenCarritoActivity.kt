package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.adapters.CarritoAdapter
import com.cibertec.tacosmarcial.models.DatosApp

class ResumenCarritoActivity : AppCompatActivity() {

    private lateinit var rvCarrito: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var txtCarritoVacio: TextView
    private lateinit var btnIrAPagar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen_carrito)

        rvCarrito = findViewById(R.id.rvCarritoResumen)
        txtTotal = findViewById(R.id.txtTotalResumen)
        txtCarritoVacio = findViewById(R.id.txtCarritoVacio)
        btnIrAPagar = findViewById(R.id.btnIrAPagar)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        rvCarrito.layoutManager = LinearLayoutManager(this)
        rvCarrito.adapter = CarritoAdapter(DatosApp.carrito) { actualizarVista() }

        btnIrAPagar.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        actualizarVista()
    }

    private fun actualizarVista() {
        if (DatosApp.carrito.isEmpty()) {
            txtCarritoVacio.visibility = View.VISIBLE
            rvCarrito.visibility = View.GONE
            btnIrAPagar.isEnabled = false
            btnIrAPagar.alpha = 0.5f
            txtTotal.text = "S/ 0.00"
        } else {
            txtCarritoVacio.visibility = View.GONE
            rvCarrito.visibility = View.VISIBLE
            btnIrAPagar.isEnabled = true
            btnIrAPagar.alpha = 1.0f
            actualizarTotal()
        }
    }

    private fun actualizarTotal() {
        var total = 0.0
        for (item in DatosApp.carrito) {
            total += item.plato.precio * item.cantidad
        }
        txtTotal.text = "S/ ${String.format("%.2f", total)}"
    }
}
