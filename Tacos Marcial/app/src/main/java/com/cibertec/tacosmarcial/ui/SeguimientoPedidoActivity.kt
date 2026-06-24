package com.cibertec.tacosmarcial.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import android.graphics.Color
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.Toast

class SeguimientoPedidoActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seguimiento_pedido)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapTracking) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnContact).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:51948327199")
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val restaurante = LatLng(-12.0028, -77.0055)
        val cliente = LatLng(-12.0125, -76.9985)


        mMap.addMarker(MarkerOptions().position(restaurante).title("Tacos Marcial (Sede Los Jardines)"))
        mMap.addMarker(MarkerOptions().position(cliente).title("Tu Ubicación"))


        mMap.addPolyline(PolylineOptions()
            .add(restaurante, cliente)
            .width(10f)
            .color(Color.RED)
        )


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurante, 14f))
    }
}
