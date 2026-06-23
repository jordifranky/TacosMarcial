package com.cibertec.tacosmarcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.Sucursal

class HorariosAdapter(private val lista: List<Sucursal>) :
    RecyclerView.Adapter<HorariosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(android.R.id.text1)
        val tvInfo: TextView = view.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.tvNombre.text = item.nombreLocal
        holder.tvInfo.text = "${item.direccion}\nHorario: ${item.horario}"
        holder.tvNombre.setTextColor(0xFFA6211F.toInt())
    }

    override fun getItemCount() = lista.size
}
