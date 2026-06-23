package com.cibertec.tacosmarcial.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R

class DetallePlatoActivity : AppCompatActivity() {

    private lateinit var txtNombre: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtPrecio: TextView
    private lateinit var imgDetalle: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detalle_plato)

        txtNombre = findViewById(R.id.txtNombre)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPrecio = findViewById(R.id.txtPrecio)
        imgDetalle = findViewById(R.id.imgDetalle)

        val nombre = intent.getStringExtra("PLATO_NOMBRE") ?: ""
        val descripcion = intent.getStringExtra("PLATO_DESCRIPCION") ?: ""
        val precio = intent.getDoubleExtra("PLATO_PRECIO", 0.0)
        val categoria = intent.getStringExtra("PLATO_CATEGORIA") ?: "Tacos"

        txtNombre.text = nombre
        txtDescripcion.text = descripcion
        txtPrecio.text = "S/ ${String.format("%.2f", precio)}"

        val imagenRes = when (categoria) {
            "Tacos" -> R.drawable.taco
            "Enchiladas" -> R.drawable.enchiladas
            "Broasters" -> R.drawable.broasters
            "Hamburguesas" -> R.drawable.hamburguesas
            "Bebidas" -> R.drawable.bebidas
            else -> R.drawable.taco
        }
        imgDetalle.setImageResource(imagenRes)
    }
}