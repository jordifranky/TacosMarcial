package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.DatosApp

class SeleccionSucursalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_sucursal)

        val radioSucursales = findViewById<RadioGroup>(R.id.radioSucursales)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)

        btnContinuar.setOnClickListener {
            if (radioSucursales.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Selecciona una sucursal", Toast.LENGTH_SHORT).show()
            } else {
                // Capturamos el texto del boton seleccionado
                val selectedButton = findViewById<RadioButton>(radioSucursales.checkedRadioButtonId)
                val nombreSucursal = selectedButton.text.toString()

                DatosApp.lugarSeleccionado = nombreSucursal

                val intent = Intent(this, MenuActivity::class.java)
                // Enviamos el nombre de la sucursal a la siguiente actividad
                intent.putExtra("NOMBRE_SUCURSAL", nombreSucursal)
                startActivity(intent)
            }
        }
    }
}