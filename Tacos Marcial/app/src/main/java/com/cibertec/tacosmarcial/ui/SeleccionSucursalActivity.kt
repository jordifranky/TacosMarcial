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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SeleccionSucursalActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_sucursal)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapSedes) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val radioSucursales = findViewById<RadioGroup>(R.id.radioSucursales)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)

        radioSucursales.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb1 -> moverAMarcador(LatLng(-12.0028, -77.0055), "Sede Los Jardines")
                R.id.rb2 -> moverAMarcador(LatLng(-12.0165, -77.0005), "Sede Mall Aventura")
                R.id.rb3 -> moverAMarcador(LatLng(-11.9754, -77.0001), "Sede Canto Grande")
                R.id.rb4 -> moverAMarcador(LatLng(-11.9564, -77.0012), "Sede Canto Rey")
            }
        }

        btnContinuar.setOnClickListener {
            if (radioSucursales.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Selecciona una sucursal", Toast.LENGTH_SHORT).show()
            } else {
                val selectedButton = findViewById<RadioButton>(radioSucursales.checkedRadioButtonId)
                val nombreSucursal = selectedButton.text.toString().split("\n")[0]

                DatosApp.lugarSeleccionado = nombreSucursal

                val lineas = selectedButton.text.toString().split("\n")
                if (lineas.size > 1) {
                    DatosApp.direccionSeleccionada = lineas[1]
                }

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun moverAMarcador(posicion: LatLng, titulo: String) {
        if (::mMap.isInitialized) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicion, 16f))
            mMap.addMarker(MarkerOptions().position(posicion).title(titulo))?.showInfoWindow()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sede1 = LatLng(-12.0028, -77.0055) // Los Jardines
        val sede2 = LatLng(-12.0165, -77.0005) // Mall Aventura
        val sede3 = LatLng(-11.9754, -77.0001) // Canto Grande
        val sede4 = LatLng(-11.9564, -77.0012) // Canto Rey

        mMap.addMarker(MarkerOptions().position(sede1).title("Sede Los Jardines"))
        mMap.addMarker(MarkerOptions().position(sede2).title("Sede Mall Aventura"))
        mMap.addMarker(MarkerOptions().position(sede3).title("Sede Canto Grande"))
        mMap.addMarker(MarkerOptions().position(sede4).title("Sede Canto Rey"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sede1, 13f))
    }
}