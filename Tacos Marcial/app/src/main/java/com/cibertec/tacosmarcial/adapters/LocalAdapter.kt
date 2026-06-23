package com.cibertec.tacosmarcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.Local

class LocalAdapter(
    private val listaLocales: List<Local>,
    private val onLocalClick: (Local) -> Unit
) : RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    class LocalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreLocal)
        val tvDireccion: TextView = view.findViewById(R.id.tvDireccionLocal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_local, parent, false)
        return LocalViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        val local = listaLocales[position]
        holder.tvNombre.text = local.nombre
        holder.tvDireccion.text = local.direccion

        holder.itemView.setOnClickListener {
            onLocalClick(local)
        }
    }

    override fun getItemCount() = listaLocales.size
}