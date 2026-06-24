package com.cibertec.tacosmarcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.Sucursal
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HorariosAdapter(private val lista: List<Sucursal>) :
    RecyclerView.Adapter<HorariosAdapter.ViewHolder>() {

    private var expandedPosition = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), OnMapReadyCallback {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreSucursal)
        val tvDireccion: TextView = view.findViewById(R.id.tvDireccionSucursal)
        val tvHorario: TextView = view.findViewById(R.id.tvHorarioSucursal)
        val btnVerMapa: LinearLayout = view.findViewById(R.id.btnVerMapa)
        val layoutMapa: LinearLayout = view.findViewById(R.id.layoutMapa)
        val imgChevron: ImageView = view.findViewById(R.id.imgChevron)
        val mapView: MapView = view.findViewById(R.id.mapLite)
        
        private var googleMap: GoogleMap? = null
        private var sucursal: Sucursal? = null

        init {
            mapView.onCreate(null)
            mapView.getMapAsync(this)
        }

        fun bind(item: Sucursal, isExpanded: Boolean) {
            sucursal = item
            tvNombre.text = item.nombreLocal
            tvDireccion.text = item.direccion
            tvHorario.text = "Horario: ${item.horario}"

            layoutMapa.visibility = if (isExpanded) View.VISIBLE else View.GONE
            imgChevron.rotation = if (isExpanded) 270f else 90f
            
            actualizarMapa()
        }

        override fun onMapReady(map: GoogleMap) {
            googleMap = map
            map.uiSettings.isMapToolbarEnabled = false
            actualizarMapa()
        }

        private fun actualizarMapa() {
            val s = sucursal ?: return
            val gMap = googleMap ?: return
            
            val pos = LatLng(s.lat, s.lng)
            gMap.clear()
            gMap.addMarker(MarkerOptions().position(pos).title(s.nombreLocal))
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15f))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sucursal_profesional, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        holder.bind(lista[position], isExpanded)

        holder.btnVerMapa.setOnClickListener {
            val previousExpanded = expandedPosition
            expandedPosition = if (isExpanded) -1 else position
            
            notifyItemChanged(previousExpanded)
            notifyItemChanged(expandedPosition)
        }
    }

    override fun getItemCount() = lista.size
}
