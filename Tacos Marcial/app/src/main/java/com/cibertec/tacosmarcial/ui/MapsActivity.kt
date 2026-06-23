package com.cibertec.tacosmarcial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.tacosmarcial.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Ubicación de Tacos Marcial
        val tacosMarcial = LatLng(-12.0464, -77.0428)

        mMap.addMarker(
            MarkerOptions()
                .position(tacosMarcial)
                .title("Tacos Marcial")
        )

        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(tacosMarcial, 15f)
        )
    }
}