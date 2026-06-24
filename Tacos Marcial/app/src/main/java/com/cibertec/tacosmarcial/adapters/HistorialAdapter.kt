package com.cibertec.tacosmarcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.HistorialPedido

class HistorialAdapter(private val listaPedidos: List<HistorialPedido>) :
    RecyclerView.Adapter<HistorialAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombrePlato)
        val tvPrecio: TextView = view.findViewById(R.id.tvPrecio)
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pedido = listaPedidos[position]


        holder.tvNombre.text = "${pedido.cantidad}x ${pedido.nombrePlato}"


        val total = pedido.precio * pedido.cantidad
        holder.tvPrecio.text = "Total: S/ ${"%.2f".format(total)} (${pedido.metodoPago})"


        holder.tvFecha.text = pedido.fecha
    }

    override fun getItemCount() = listaPedidos.size
}