package com.cibertec.tacosmarcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.tacosmarcial.R
import com.cibertec.tacosmarcial.models.CarritoItem

class CarritoAdapter(
    private val listaCarrito: MutableList<CarritoItem>,
    private val onCantidadCambiada: () -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre = itemView.findViewById<TextView>(R.id.tvNombreCarrito)
        val tvPrecio = itemView.findViewById<TextView>(R.id.tvPrecioCarrito)
        val tvCantidad = itemView.findViewById<TextView>(R.id.tvCantidadCarrito)
        val tvSubtotal = itemView.findViewById<TextView>(R.id.tvSubtotalCarrito)
        val btnMas = itemView.findViewById<ImageButton>(R.id.btnMas)
        val btnMenos = itemView.findViewById<ImageButton>(R.id.btnMenos)
        val btnEliminar = itemView.findViewById<ImageButton>(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val item = listaCarrito[position]

        holder.tvNombre.text = item.plato.nombre
        holder.tvPrecio.text = "Precio: S/ ${String.format("%.2f", item.plato.precio)}"
        holder.tvCantidad.text = item.cantidad.toString()
        actualizarSubtotal(holder, item)

        holder.btnMas.setOnClickListener {
            item.cantidad++
            holder.tvCantidad.text = item.cantidad.toString()
            actualizarSubtotal(holder, item)
            onCantidadCambiada()
        }

        holder.btnMenos.setOnClickListener {
            if (item.cantidad > 1) {
                item.cantidad--
                holder.tvCantidad.text = item.cantidad.toString()
                actualizarSubtotal(holder, item)
                onCantidadCambiada()
            }
        }

        holder.btnEliminar.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                listaCarrito.removeAt(pos)
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, listaCarrito.size)
                onCantidadCambiada()
            }
        }
    }

    private fun actualizarSubtotal(holder: CarritoViewHolder, item: CarritoItem) {
        val subtotal = item.plato.precio * item.cantidad
        holder.tvSubtotal.text = "S/ ${String.format("%.2f", subtotal)}"
    }

    override fun getItemCount(): Int = listaCarrito.size
}
