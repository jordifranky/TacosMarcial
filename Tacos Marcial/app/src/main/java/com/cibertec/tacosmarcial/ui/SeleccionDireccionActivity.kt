package com.cibertec.tacosmarcial.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.DatosApp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class SeleccionDireccionActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_direccion)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapDireccion) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val etDireccion = findViewById<EditText>(R.id.etDireccionManual)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmarDireccion)

        btnConfirmar.setOnClickListener {
            val direccion = etDireccion.text.toString().trim()
            if (direccion.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu dirección", Toast.LENGTH_SHORT).show()
            } else {
                DatosApp.direccionSeleccionada = direccion
                DatosApp.lugarSeleccionado = "Domicilio Particular"
                
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sjl = LatLng(-11.99, -77.00)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sjl, 14f))
    }
}
